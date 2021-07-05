package nl.beyco.webserver.business.impl;

import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.states.BeycoContractState;
import nl.beyco.webserver.business.exceptions.BeycoFlowException;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.business.ContractService;
import nl.beyco.webserver.business.RPCService;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
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

    @Override
    public void saveContract(String issuerId, Contract contract) {
        CordaRPCOps proxy = rpcService.getProxy();
        try {
            String contractJson = "";
            String[] coffeeJson = new String[2];
            String[] conditionsJson = new String[2];
            String[] addendaJson = new String[2];

            proxy.startTrackedFlowDynamic(SaveContractFlow.class, issuerId, contractJson, coffeeJson, conditionsJson, addendaJson).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the save contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the save contract flow", e);
        }
    }

    @Override
    public Contract getContract(String issuerId, String contractId) {
        CordaRPCOps proxy = rpcService.getProxy();
        try {
            BeycoContractState result = proxy.startTrackedFlowDynamic(GetContractFlow.class, issuerId, contractId).getReturnValue().get();
           return null;
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the get contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the get contract flow", e);
        }
    }

    @Override
    public void addAddendum(String issuerId, String contractId, Addendum addendum) {
        CordaRPCOps proxy = rpcService.getProxy();
        try {
            proxy.startTrackedFlowDynamic(AddAddendumFlow.class, issuerId, contractId, addendum).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the add addendum flow", e);
            throw new BeycoFlowException("Something went wrong while calling the add addendum flow", e);
        }
    }
}
