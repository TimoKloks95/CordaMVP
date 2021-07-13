package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.corda.core.contracts.StateAndRef;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;
import nl.beyco.states.ContractJsonWithAddendaJson;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@InitiatingFlow
@StartableByRPC
public class GetContractFlow extends FlowLogic<ContractJsonWithAddendaJson> {
    private String issuerId;
    private String contractId;


    public GetContractFlow(String issuerId, String contractId) {
        this.issuerId = issuerId;
        this.contractId = contractId;
    }

    @Suspendable
    @Override
    public ContractJsonWithAddendaJson call() throws FlowException {
        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList(contractId));
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED);
        Vault.Page<BeycoContractState> vaultContracts = getContractById(linearStateQueryCriteria, criteria);

        if(vaultContracts.getStates().size() == 0) {
            throw new FlowException("The contract that you tried to retrieve doesn't exist.");
        }

        BeycoContractState contract = vaultContracts.getStates().get(0).getState().component1();

        Vault.Page<Addendum> vaultAddenda = getAddendaById(linearStateQueryCriteria, criteria);
        List<Addendum> addenda = new LinkedList<>();
        for (StateAndRef<Addendum> addendumRef : vaultAddenda.getStates()) {
            addenda.add(addendumRef.getState().component1());
        }

        if(issuerIsNotSellerAndNotBuyer(contract.getSellerId(), contract.getBuyerId())) {
            throw new FlowException("The issuer of the contract has to be either the seller or the buyer.");
        }

        String contractJson;
        try {
            contractJson = contract.toJson();
        } catch(JsonProcessingException e) {
            throw new FlowException("Something went wrong while trying to parse the contract to json format.", e);
        }

        String[] addendaJson = new String[vaultAddenda.getStates().size()];
        try {
            for(int i=0; i<addenda.size(); i++) {
                addendaJson[i] = addenda.get(i).toJson();
            }
        } catch(JsonProcessingException e) {
            throw new FlowException("Something went wrong while trying to parse an addendum to json format.", e);
        }

        return new ContractJsonWithAddendaJson(contractJson, addendaJson);
    }

    private boolean issuerIsNotSellerAndNotBuyer(String sellerId, String buyerId) {
        return !issuerId.equals(sellerId) && !issuerId.equals(buyerId);
    }

    private Vault.Page<BeycoContractState> getContractById(QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria, QueryCriteria.VaultQueryCriteria criteria) {
        return getServiceHub().getVaultService().queryBy(BeycoContractState.class, criteria.and(linearStateQueryCriteria));
    }

    private Vault.Page<Addendum> getAddendaById(QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria, QueryCriteria.VaultQueryCriteria criteria) {
        return getServiceHub().getVaultService().queryBy(Addendum.class, criteria.and(linearStateQueryCriteria));
    }
}
