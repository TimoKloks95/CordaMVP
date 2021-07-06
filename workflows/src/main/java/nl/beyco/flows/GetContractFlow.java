package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.utilities.ProgressTracker;
import nl.beyco.states.BeycoContractState;

import java.util.Collections;

@InitiatingFlow
@StartableByRPC
public class GetContractFlow extends FlowLogic<String> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    private String issuerId;
    private String contractId;
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    public GetContractFlow(String issuerId, String contractId) {
        this.issuerId = issuerId;
        this.contractId = contractId;
    }

    @Suspendable
    @Override
    public String call() throws FlowException {
        //Retrieve the contract uit de blockchain
        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList(contractId));
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED);
        Vault.Page<BeycoContractState> results = getServiceHub().getVaultService()
                .queryBy(BeycoContractState.class, criteria.and(linearStateQueryCriteria));

        //Bestaat het contract?
        if(results.getStates().size() == 0) {
            throw new FlowException("The contract that you tried to retrieve doesn't exist.");
        }
        BeycoContractState contract = results.getStates().get(0).getState().component1();

        // Check of de issuer de seller / buyer is
        if(!issuerId.equals(contract.getBuyerId()) && !issuerId.equals(contract.getSellerId())) {
            throw new FlowException("The issuer of the contract has to be either the seller or the buyer.");
        }

        // Convert BeycoContractState to String and send
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String contractJson;
        try {
            contractJson = writer.writeValueAsString(contract);
        } catch(JsonProcessingException e) {
            throw new FlowException("Something went wrong while trying to parse the contract to json format.", e);
        }

        return contractJson;
    }
}
