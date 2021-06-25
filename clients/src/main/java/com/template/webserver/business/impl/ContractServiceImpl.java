package com.template.webserver.business.impl;

import com.template.flows.OphalenContractFlow;
import com.template.flows.OpslaanContractFlow;
import com.template.flows.ToevoegenAddendumFlow;
import com.template.webserver.business.exceptions.BeycoFlowException;
import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;
import com.template.webserver.business.ContractService;
import com.template.webserver.business.RPCService;
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
    public void opslaanContract(String issuerId, Contract contract) {
        try {
            rpcService.getProxy().startTrackedFlowDynamic(OpslaanContractFlow.class, issuerId, contract).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the save contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the save contract flow", e);
        }
    }

    @Override
    public Contract ophalenContract(String issuerId, String contractId) {
        try {
            SignedTransaction result = rpcService.getProxy().startTrackedFlowDynamic(OphalenContractFlow.class, issuerId, contractId).getReturnValue().get();
           return null;
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the get contract flow", e);
            throw new BeycoFlowException("Something went wrong while calling the get contract flow", e);
        }
    }

    @Override
    public void toevoegenAddendum(String issuerId, String contractId, Addendum addendum) {
        try {
            rpcService.getProxy().startTrackedFlowDynamic(ToevoegenAddendumFlow.class, issuerId, contractId, addendum).getReturnValue().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Something went wrong while calling the add addendum flow", e);
            throw new BeycoFlowException("Something went wrong while calling the add addendum flow", e);
        }
    }
}
