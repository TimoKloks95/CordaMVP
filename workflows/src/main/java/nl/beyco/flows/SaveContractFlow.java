package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import nl.beyco.contracts.BeycoContract;
import nl.beyco.states.BeycoContractState;

import java.util.Collections;
import java.util.HashSet;

@InitiatingFlow
@StartableByRPC
public class SaveContractFlow extends FlowLogic<SignedTransaction> {
    private final String issuerId;
    private final String contractJson;

    public SaveContractFlow(String issuerId, String contractJson) {
        this.issuerId = issuerId;
        this.contractJson = contractJson;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        Party node = getOurIdentity();
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        BeycoContractState toAddState;
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            toAddState = objectMapper.readValue(contractJson, BeycoContractState.class);
        } catch(JsonProcessingException e) {
            throw new FlowException("Something went wrong trying to parse contract json to state object", e);
        }

        if(issuerIsNotSellerAndNotBuyer(toAddState.getSellerId(), toAddState.getBuyerId())) {
            throw new FlowException("The issuer of the contract has to be either the seller or the buyer.");
        }

        if(contractAlreadyExistsInVault(toAddState.getId())) {
            throw new FlowException("The contract that you tried to save already exists.");
        }

        toAddState.setNode(node);

        final TransactionBuilder builder = new TransactionBuilder(notary);
        builder.addOutputState(toAddState);
        builder.addCommand(new BeycoContract.Commands.Save(), Collections.singletonList(node.getOwningKey()));

        final SignedTransaction selfSignedTx = getServiceHub().signInitialTransaction(builder);

        return subFlow(new FinalityFlow(selfSignedTx, new HashSet<FlowSession>(0)));
    }

    private boolean issuerIsNotSellerAndNotBuyer(String sellerId, String buyerId) {
        return !issuerId.equals(sellerId) && !issuerId.equals(buyerId);
    }

    private boolean contractAlreadyExistsInVault(String contractId) {
        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList(contractId));
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED);
        Vault.Page<BeycoContractState> contracts = getServiceHub().getVaultService().queryBy(BeycoContractState.class, criteria.and(linearStateQueryCriteria));
        return contracts.getStates().size() != 0;
    }
}
