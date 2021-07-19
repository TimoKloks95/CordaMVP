package nl.beyco.webserver.blockchain.impl;

import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.blockchain.BeycoProxy;
import nl.beyco.webserver.blockchain.RPCInteractionService;
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
    private BeycoProxy proxy;

    @Override
    public void startSaveContractFlow(String issuerId, String contractJson) {
        log.info("Attempting to call the saveContract flow.");
        try {
            proxy.startSaveContractFlow(issuerId, contractJson);
        } catch (InterruptedException | ExecutionException e) {
            if(e instanceof InterruptedException) {
                log.error("Save contract flow was interrupted.");
                Thread.currentThread().interrupt();
            }
            log.error("Something went wrong while calling the saveContract flow.", e);
            throw new BeycoFlowException("Something went wrong while calling the saveContract flow.", e);
        }
    }

    @Override
    public ContractJsonWithAddendaJson startGetContractFlow(String issuerId, String contractId) {
        log.info("Attempting to call the getContract flow.");
        try {
            return proxy.startGetContractFlow(issuerId, contractId);
        } catch (InterruptedException | ExecutionException e) {
            if(e instanceof InterruptedException) {
                log.error("Get contract flow was interrupted.");
                Thread.currentThread().interrupt();
            }
            log.error("Something went wrong while calling the getContract flow.", e);
            throw new BeycoFlowException("Something went wrong while calling the getContract flow.", e);
        }
    }

    @Override
    public void startAddAddendumFlow(String issuerId, String contractId, String addendumJson) {
        log.info("Attempting to call the addAddendum flow.");
        try {
            proxy.startAddAddendumFlow(issuerId, contractId, addendumJson);
        } catch (InterruptedException | ExecutionException e) {
            if(e instanceof InterruptedException) {
                log.error("Add addendum flow was interrupted.");
                Thread.currentThread().interrupt();
            }
            log.error("Something went wrong while calling the addAddendum flow.", e);
            throw new BeycoFlowException("Something went wrong while calling the addAddendum flow.", e);
        }
    }
}
