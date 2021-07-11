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
import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;
import java.util.Collections;
import java.util.HashSet;

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

        BeycoContractState inputContractState = results.getStates().get(0).getState().component1();

        if(issuerIsNotSellerAndNotBuyer(inputContractState.getSellerId(), inputContractState.getBuyerId())) {
            throw new FlowException("The issuer of the addendum has to be either the seller or the buyer of the contract.");
        }

        if(issuerIsNotSellerAndNotBuyer(toAddAddendum.getSellerId(), toAddAddendum.getBuyerId())) {
            throw new FlowException("The issuer of the addendum has to be either the seller or the buyer of the addendum.");
        }

        if(addendumParticipantsAreNotEqualToContractParticipants(inputContractState, toAddAddendum)) {
            throw new FlowException("The addendum needs to have the same seller and buyer as the contract that it is being added to.");
        }

        if(addendumSignedIsBeforeContractSigned(inputContractState, toAddAddendum)) {
            throw new FlowException("The signed time of the addendum can't be before the signed time of the contract that it is being added to.");
        }

        final TransactionBuilder builder = new TransactionBuilder(notary);
            builder.addOutputState(toAddAddendum);
            builder.addCommand(new BeycoContract.Commands.Add(), Collections.singletonList(node.getOwningKey()));

        final SignedTransaction selfSignedTx = getServiceHub().signInitialTransaction(builder);

        return subFlow(new FinalityFlow(selfSignedTx, new HashSet<FlowSession>(0)));
    }

    private boolean addendumSignedIsBeforeContractSigned(BeycoContractState inputContractState, Addendum toAddAddendum) {
        return (toAddAddendum.getSellerSignedAt().isBefore(inputContractState.getSellerSignedAt()) || toAddAddendum.getSellerSignedAt().isBefore(inputContractState.getBuyerSignedAt()))
                ||
                (toAddAddendum.getBuyerSignedAt().isBefore(inputContractState.getSellerSignedAt()) || toAddAddendum.getBuyerSignedAt().isBefore(inputContractState.getBuyerSignedAt()));
    }

    private boolean addendumParticipantsAreNotEqualToContractParticipants(BeycoContractState inputContractState, Addendum toAddAddendum) {
        return !inputContractState.getSellerId().equals(toAddAddendum.getSellerId()) || !inputContractState.getBuyerId().equals(toAddAddendum.getBuyerId());
    }

    private boolean issuerIsNotSellerAndNotBuyer(String sellerId, String buyerId) {
        return !issuerId.equals(sellerId) && !issuerId.equals(buyerId);
    }
}
