package nl.beyco.webserver.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.webserver.business.exceptions.BeycoFlowException;
import nl.beyco.webserver.business.exceptions.BeycoParseException;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractService;
import nl.beyco.webserver.business.RPCService;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import nl.beyco.webserver.dto.ToAddAddendum;
import nl.beyco.webserver.helpers.ClientSerializationHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class ContractServiceImpl implements ContractService {
    private static final Logger log = LogManager.getLogger(ContractServiceImpl.class);

    @Autowired
    private RPCService rpcService;

    @Autowired
    private ClientSerializationHelper serializationHelper;

    @Override
    public void saveContract(Contract contract) {
        CordaRPCOps proxy = rpcService.getProxy();
        String contractJson;
        try {
            contractJson = serializationHelper.contractToContractJson(contract);
            log.info(contractJson);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse the contract to json format.", e);
            throw new BeycoParseException("Something went wrong while trying to parse the contract to json format.", e);
        }
        try {
            proxy.startTrackedFlowDynamic(SaveContractFlow.class, contractJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the save contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the save contract flow", e);
        }
    }

    @Override
    public Contract getContract(String issuerId, String contractId) {
        CordaRPCOps proxy = rpcService.getProxy();
        String result;
        try {
            result = proxy.startTrackedFlowDynamic(GetContractFlow.class, issuerId, contractId).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the get contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the get contract flow", e);
        }
        Contract contract;
        try {
            contract = serializationHelper.contractJsonToContract(result);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse json string to contract.", e);
            throw new BeycoParseException("Something went wrong while trying to parse json string to contract.", e);
        }
        return contract;
    }

    @Override
    public void addAddendum(String contractId, ToAddAddendum addendum) {
        CordaRPCOps proxy = rpcService.getProxy();
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        ObjectWriter writer = mapper.writer().withDefaultPrettyPrinter();
        String addendumJson;
        try {
            addendumJson = writer.writeValueAsString(addendum);
            log.info(addendumJson);
        } catch(JsonProcessingException e) {
            log.error("Something went wrong while trying to parse the addendum to json format.", e);
            throw new BeycoParseException("Something went wrong while trying to parse the addendum to json format.", e);
        }
        try {
            proxy.startTrackedFlowDynamic(AddAddendumFlow.class, addendum.getIssuerId(), addendumJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the add addendum flow", e);
            throw new BeycoFlowException("Something went wrong while calling the add addendum flow", e);
        }
    }
}
