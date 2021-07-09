package nl.beyco.webserver.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.util.Pair;
import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.business.exceptions.BeycoFlowException;
import nl.beyco.webserver.business.exceptions.BeycoParseException;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractService;
import nl.beyco.webserver.business.RPCService;
import net.corda.core.messaging.CordaRPCOps;
import nl.beyco.webserver.helpers.ClientSerializationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ContractServiceImpl implements ContractService {
    private static final Logger log = LogManager.getLogger(ContractServiceImpl.class);

    @Autowired
    private RPCService rpcService;

    @Autowired
    private ClientSerializationHelper serializationHelper;

    @Override
    public void saveContract(String issuerId, Contract contract) {
        CordaRPCOps proxy = rpcService.getProxy();
        String contractJson;

        try {
            contractJson = serializationHelper.contractToContractJson(contract);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse the contract to json format.", e);
            throw new BeycoParseException("Something went wrong while trying to parse the contract to json format.", e);
        }

        try {
            proxy.startTrackedFlowDynamic(SaveContractFlow.class, issuerId, contractJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the save contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the save contract flow", e);
        }
    }

    @Override
    public Pair<Contract, List<Addendum>> getContract(String issuerId, String contractId) {
        CordaRPCOps proxy = rpcService.getProxy();
        ContractJsonWithAddendaJson result;

        try {
            result = proxy.startTrackedFlowDynamic(GetContractFlow.class, issuerId, contractId).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the get contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the get contract flow", e);
        }

        Contract contract;
        List<Addendum> addenda = new LinkedList<>();

        try {
            contract = serializationHelper.contractJsonToContract(result.getContractJson());
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse json string to contract.", e);
            throw new BeycoParseException("Something went wrong while trying to parse json string to contract.", e);
        }

        try {
            for(int i=0; i<result.getAddendaJson().length; i++) {
                addenda.add(serializationHelper.addendumJsonToAddendum(result.getAddendaJson()[i]));
            }
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse json string to addendum", e);
            throw new BeycoParseException("Something went wrong while trying to parse json string to addendum", e);
        }

        return new Pair<>(contract, addenda);
    }

    @Override
    public void addAddendum(String issuerId, Addendum addendum) {
        CordaRPCOps proxy = rpcService.getProxy();
        String addendumJson;

        try {
            addendumJson = serializationHelper.addendumToAddendumJson(addendum);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse the addendum to json format.", e);
            throw new BeycoParseException("Something went wrong while trying to parse the addendum to json format.", e);
        }

        try {
            proxy.startTrackedFlowDynamic(AddAddendumFlow.class, issuerId, addendumJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the add addendum flow", e);
            throw new BeycoFlowException("Something went wrong while calling the add addendum flow", e);
        }
    }
}
