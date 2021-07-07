package nl.beyco.webserver.business;

import net.corda.core.transactions.SignedTransaction;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.dto.ToAddAddendum;

public interface ContractService {
    void saveContract(Contract contract);
    Contract getContract(String issuerId, String contractId);
    void addAddendum(String contractId, ToAddAddendum addendum);
}
