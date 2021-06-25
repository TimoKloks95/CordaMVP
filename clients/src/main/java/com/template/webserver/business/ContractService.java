package com.template.webserver.business;

import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;

public interface ContractService {
    void opslaanContract(String issuerId, Contract contract);
    Contract ophalenContract(String issuerId, String contractId);
    void toevoegenAddendum(String issuerId, String contractId, Addendum addendum);
}
