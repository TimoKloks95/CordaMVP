package nl.beyco.webserver.blockchain;

import nl.beyco.states.ContractJsonWithAddendaJson;

public interface RPCInteractionService {
    void startSaveContractFlow(String issuerId, String contractJson);
    ContractJsonWithAddendaJson startGetContractFlow(String issuerId, String contractId);
    void startAddAddendumFlow(String issuerId, String contractId, String addendumJson);
}
