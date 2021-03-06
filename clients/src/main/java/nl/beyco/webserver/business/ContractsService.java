package nl.beyco.webserver.business;

import javafx.util.Pair;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;

import java.util.List;

public interface ContractsService {
    void saveContract(String issuerId, Contract contract);
    Pair<Contract, List<Addendum>> getContract(String issuerId, String contractId);
    void addAddendum(String issuerId, String contractId, Addendum addendum);
}
