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
    private static final ProgressTracker.Step BEFORE_SET_ADDENDUM = new ProgressTracker.Step("Before addendum is set.");
    private static final ProgressTracker.Step AFTER_SET_ADDENDUM = new ProgressTracker.Step("After addendum is set.");
    private final ProgressTracker progressTracker = new ProgressTracker(BEFORE_SET_ADDENDUM, AFTER_SET_ADDENDUM);
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }
    private String issuerId;
    private String contractId;
    private String addendumJson;

    public AddAddendumFlow(String issuerId, String contractId, String addendumJson) {
        this.issuerId = issuerId;
        this.contractId = contractId;
        this.addendumJson = addendumJson;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        Party node = getOurIdentity();
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        //Ophalen van contract
        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList(contractId));
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED);
        Vault.Page<BeycoContractState> results = getServiceHub().getVaultService().queryBy(BeycoContractState.class, criteria.and(linearStateQueryCriteria));

        //Bestaat dit contract?
        if(results.getStates().size() == 0) {
            throw new FlowException("The contract that you tried to retrieve doesn't exist.");
        }
        //Ophalen input
        StateAndRef<BeycoContractState> inputContractStateAndRef = results.getStates().get(0);
        //Ophalen input state
        BeycoContractState inputContractState = results.getStates().get(0).getState().component1();

        //Is de issuer de seller of buyer van dit contract?
        if(issuerIsNotSellerAndNotBuyer(inputContractState.getSellerId(), inputContractState.getBuyerId())) {
            throw new FlowException("The issuer of the contract has to be either the seller or the buyer.");
        }

        //Deserializeren van addendumJson
        Addendum toAddAddendum;
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            toAddAddendum = objectMapper.readValue(addendumJson, Addendum.class);
        } catch(JsonProcessingException e) {
            throw new FlowException("Something went wrong trying to parse addendum json to object", e);
        }

        //Aanmaken van output state
        BeycoContractState outputContract = inputContractState.copyWithNewAddendum(toAddAddendum);
        outputContract.setNode(node);

        //Opstellen van transactionbuilder
        final TransactionBuilder builder = new TransactionBuilder(notary);
            builder.addInputState(inputContractStateAndRef);
            builder.addOutputState(outputContract);
            builder.addCommand(new BeycoContract.Commands.Add(), Arrays.asList(node.getOwningKey()));

        //Tekenen van transactie
        final SignedTransaction selfSignedTx = getServiceHub().signInitialTransaction(builder);

        //Notarisen
        return subFlow(new FinalityFlow(selfSignedTx, new HashSet<FlowSession>(0)));
    }

    private boolean issuerIsNotSellerAndNotBuyer(String sellerId, String buyerId) {
        return !issuerId.equals(sellerId) && !issuerId.equals(buyerId);
    }
}
