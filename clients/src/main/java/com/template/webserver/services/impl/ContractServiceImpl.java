package com.template.webserver.services.impl;

import com.template.flows.OphalenContractFlow;
import com.template.flows.OpslaanContractFlow;
import com.template.flows.ToevoegenAddendumFlow;
import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;
import com.template.webserver.services.ContractService;
import com.template.webserver.services.RPCService;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import org.springframework.stereotype.Component;

@Component
public class ContractServiceImpl implements ContractService {
    private final CordaRPCOps proxy;

    public ContractServiceImpl(RPCService rpcService) {
        this.proxy = rpcService.getProxy();
    }

    @Override
    public Contract opslaanContract(String issuerId, Contract contract) {
        try {
            SignedTransaction result = proxy.startTrackedFlowDynamic(OpslaanContractFlow.class, issuerId, contract).getReturnValue().get();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Contract ophalenContract(String issuerId, String contractId) {
        try {
            SignedTransaction result = proxy.startTrackedFlowDynamic(OphalenContractFlow.class, issuerId, contractId).getReturnValue().get();
           return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Contract toevoegenAddendum(String issuerId, String contractId, Addendum addendum) {
        try {
            SignedTransaction result = proxy.startTrackedFlowDynamic(ToevoegenAddendumFlow.class, issuerId, contractId, addendum).getReturnValue().get();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
