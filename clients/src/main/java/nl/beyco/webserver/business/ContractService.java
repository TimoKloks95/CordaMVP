package nl.beyco.webserver.business;

import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;

public interface ContractService {
    void saveContract(String issuerId, Contract contract);
    Contract getContract(String issuerId, String contractId);
    void addAddendum(String issuerId, String contractId, Addendum addendum);
}
