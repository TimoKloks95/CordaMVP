package nl.beyco.webserver.blockchain;

import net.corda.core.transactions.SignedTransaction;
import nl.beyco.states.ContractJsonWithAddendaJson;

public interface RPCInteractionService {
    SignedTransaction startSaveContractFlow(String issuerId, String contractJson);
    ContractJsonWithAddendaJson startGetContractFlow(String issuerId, String contractId);
    SignedTransaction startAddAddendumFlow(String issuerId, String contractId, String addendumJson);
}
