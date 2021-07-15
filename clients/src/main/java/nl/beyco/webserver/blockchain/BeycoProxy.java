package nl.beyco.webserver.blockchain;

import nl.beyco.states.ContractJsonWithAddendaJson;

import java.util.concurrent.ExecutionException;

public interface BeycoProxy {
    void startSaveContractFlow(String issuerId, String contractJson) throws InterruptedException, ExecutionException;
    ContractJsonWithAddendaJson startGetContractFlow(String issuerId, String contractId) throws InterruptedException, ExecutionException;
    void startAddAddendumFlow(String issuerId, String contractId, String addendumJson) throws InterruptedException, ExecutionException;
}
