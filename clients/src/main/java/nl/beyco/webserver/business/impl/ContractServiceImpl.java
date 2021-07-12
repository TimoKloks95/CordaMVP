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
        log.info("Attempting to parse contract with ID: "+contract.getId()+ "to json.");
        try {
            contractJson = serializationHelper.contractToContractJson(contract);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse contract with ID: "+contract.getId()+" to json.", e);
            throw new BeycoParseException("Something went wrong while trying to parse contract with ID: "+contract.getId()+" to json.", e);
        }
        log.info("Succesfully parsed contract with ID: "+contract.getId()+ "to json.");
        log.info("Attempting to call the saveContract flow to save contract with ID: "+contract.getId()+" in the blockchain.");
        try {
            proxy.startTrackedFlowDynamic(SaveContractFlow.class, issuerId, contractJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the saveContract flow to save contract with ID: "+contract.getId()+" in the blockchain.", e);
            throw new BeycoFlowException("Something went wrong while calling the saveContract flow to save contract with ID: "+contract.getId()+" in the blockchain.", e);
        }
        log.info("Succesfully saved contract with ID: "+contract.getId()+" in the blockchain.");
    }

    @Override
    public Pair<Contract, List<Addendum>> getContract(String issuerId, String contractId) {
        CordaRPCOps proxy = rpcService.getProxy();
        ContractJsonWithAddendaJson result;
        log.info("Attempting to call the getContract flow to get contract with ID: "+contractId+" from the blockchain.");
        try {
            result = proxy.startTrackedFlowDynamic(GetContractFlow.class, issuerId, contractId).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the getContract flow to get contract with ID: "+contractId+" from the blockchain.", e);
            throw new BeycoFlowException("Something went wrong while calling the getContract flow to get contract with ID: "+contractId+" from the blockchain.", e);
        }
        log.info("Succesfully retrieved contract with ID: "+contractId+" from the blockchain.");
        Contract contract;
        List<Addendum> addenda = new LinkedList<>();
        log.info("Attempting to parse retrieved contract json to contract object.");
        try {
            contract = serializationHelper.contractJsonToContract(result.getContractJson());
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse contract json to contract object.", e);
            throw new BeycoParseException("Something went wrong while trying to parse contract json to contract object.", e);
        }
        log.info("Succesfully parsed contract json to contract object.");
        log.info("Attempting to parse retrieved addendum json to addendum object.");
        try {
            for(int i=0; i<result.getAddendaJson().length; i++) {
                addenda.add(serializationHelper.addendumJsonToAddendum(result.getAddendaJson()[i]));
            }
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse addendum json string to addendum object.", e);
            throw new BeycoParseException("Something went wrong while trying to parse addendum json string to addendum object.", e);
        }
        log.info("Succesfully parsed addendum json to addendum object.");
        return new Pair<>(contract, addenda);
    }

    @Override
    public void addAddendum(String issuerId, String contractId, Addendum addendum) {
        CordaRPCOps proxy = rpcService.getProxy();
        String addendumJson;
        log.info("Attempting to parse addendum with ID: "+addendum.getId()+ "to json.");
        try {
            addendumJson = serializationHelper.addendumToAddendumJson(addendum);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse addendum with ID: "+addendum.getId()+"to json.", e);
            throw new BeycoParseException("Something went wrong while trying to parse addendum with ID: "+addendum.getId()+"to json.", e);
        }
        log.info("Succesfully parsed addendum with ID: "+addendum.getId()+ "to json.");
        log.info("Attempting to call the addAddendum flow to add addendum with ID: "+addendum.getId()+" to contract with ID: "+contractId+" in the blockchain.");
        try {
            proxy.startTrackedFlowDynamic(AddAddendumFlow.class, issuerId, contractId, addendumJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the addAddendum flow to add addendum with ID: "+addendum.getId()+" to contract with ID: "+contractId+" in the blockchain.", e);
            throw new BeycoFlowException("Something went wrong while calling the addAddendum flow to add addendum with ID: "+addendum.getId()+" to contract with ID: "+contractId+" in the blockchain.", e);
        }
        log.info("Succesfully added addendum with ID: "+addendum.getId()+" to the contract with ID: "+contractId+" in the blockchain.");
    }
}
