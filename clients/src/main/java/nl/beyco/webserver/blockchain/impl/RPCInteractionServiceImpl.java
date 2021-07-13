package nl.beyco.webserver.blockchain.impl;

import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.blockchain.RPCInteractionService;
import nl.beyco.webserver.blockchain.RPCConnectionService;
import nl.beyco.webserver.business.exceptions.BeycoFlowException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class RPCInteractionServiceImpl implements RPCInteractionService {
    private static final Logger log = LogManager.getLogger(RPCInteractionServiceImpl.class);

    @Autowired
    RPCConnectionService rpcConnectionService;

    @Override
    public SignedTransaction startSaveContractFlow(String issuerId, String contractJson) {
        CordaRPCOps proxy = rpcConnectionService.getProxy();
        log.info("Attempting to call the saveContract flow.");
        try {
            return proxy.startTrackedFlowDynamic(SaveContractFlow.class, issuerId, contractJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the saveContract flow.", e);
            throw new BeycoFlowException("Something went wrong while calling the saveContract flow.", e);
        }
    }

    @Override
    public ContractJsonWithAddendaJson startGetContractFlow(String issuerId, String contractId) {
        CordaRPCOps proxy = rpcConnectionService.getProxy();
        log.info("Attempting to call the getContract flow to get contract with ID: "+contractId+" from the blockchain.");
        try {
            return proxy.startTrackedFlowDynamic(GetContractFlow.class, issuerId, contractId).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the getContract flow to get contract with ID: "+contractId+" from the blockchain.", e);
            throw new BeycoFlowException("Something went wrong while calling the getContract flow to get contract with ID: "+contractId+" from the blockchain.", e);
        }
    }

    @Override
    public SignedTransaction startAddAddendumFlow(String issuerId, String contractId, String addendumJson) {
        CordaRPCOps proxy = rpcConnectionService.getProxy();
        log.info("Attempting to call the addAddendum flow.");
        try {
            return proxy.startTrackedFlowDynamic(AddAddendumFlow.class, issuerId, contractId, addendumJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the addAddendum flow.", e);
            throw new BeycoFlowException("Something went wrong while calling the addAddendum flow.", e);
        }
    }
}
