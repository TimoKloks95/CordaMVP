package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.transactions.TransactionBuilder;
import net.corda.core.utilities.ProgressTracker;
import nl.beyco.contracts.BeycoContract;
import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@InitiatingFlow
@StartableByRPC
public class AddAddendumFlow extends FlowLogic<SignedTransaction> {
    private String issuerId;
    private String addendumJson;

    public AddAddendumFlow(String issuerId, String addendumJson) {
        this.issuerId = issuerId;
        this.addendumJson = addendumJson;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        Party node = getOurIdentity();
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        Addendum toAddAddendum;
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            toAddAddendum = objectMapper.readValue(addendumJson, Addendum.class);
        } catch(JsonProcessingException e) {
            throw new FlowException("Something went wrong trying to parse addendum json to object", e);
        }
        toAddAddendum.setNode(node);

        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList(toAddAddendum.getContractId()));
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED);
        Vault.Page<BeycoContractState> results = getServiceHub().getVaultService().queryBy(BeycoContractState.class, criteria.and(linearStateQueryCriteria));

        if(results.getStates().size() == 0) {
            throw new FlowException("The contract that you tried to add the addendum to doesn't exist.");
        }

        StateAndRef<BeycoContractState> inputContractStateAndRef = results.getStates().get(0);
        BeycoContractState inputContractState = results.getStates().get(0).getState().component1();

        if(issuerIsNotSellerAndNotBuyer(inputContractState.getSellerId(), inputContractState.getBuyerId())) {
            throw new FlowException("The issuer of the contract has to be either the seller or the buyer.");
        }

        final TransactionBuilder builder = new TransactionBuilder(notary);
            builder.addReferenceState(inputContractStateAndRef.referenced());
            builder.addOutputState(toAddAddendum);
            builder.addCommand(new BeycoContract.Commands.Add(), Collections.singletonList(node.getOwningKey()));

        final SignedTransaction selfSignedTx = getServiceHub().signInitialTransaction(builder);

        return subFlow(new FinalityFlow(selfSignedTx, new HashSet<FlowSession>(0)));
    }

    private boolean issuerIsNotSellerAndNotBuyer(String sellerId, String buyerId) {
        return !issuerId.equals(sellerId) && !issuerId.equals(buyerId);
    }
}
