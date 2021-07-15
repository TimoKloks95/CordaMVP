package nl.beyco.webserver.blockchain.impl;

import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.blockchain.BeycoProxy;
import nl.beyco.webserver.blockchain.RPCConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class BeycoProxyImpl implements BeycoProxy {
    @Autowired
    private RPCConnectionService connectionService;

    @Override
    public void startSaveContractFlow(String issuerId, String contractJson) throws InterruptedException, ExecutionException {
        connectionService.getProxy().startFlowDynamic(SaveContractFlow.class, issuerId, contractJson).getReturnValue().get();
    }

    @Override
    public ContractJsonWithAddendaJson startGetContractFlow(String issuerId, String contractId) throws InterruptedException, ExecutionException {
        return connectionService.getProxy().startFlowDynamic(GetContractFlow.class, issuerId, contractId).getReturnValue().get();
    }

    @Override
    public void startAddAddendumFlow(String issuerId, String contractId, String addendumJson) throws InterruptedException, ExecutionException {
        connectionService.getProxy().startFlowDynamic(AddAddendumFlow.class, issuerId, contractId, addendumJson).getReturnValue().get();
    }
}
