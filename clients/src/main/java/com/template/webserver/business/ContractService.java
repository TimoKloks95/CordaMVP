package com.template.webserver.business;

import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;

public interface ContractService {
    void saveContract(String issuerId, Contract contract);
    Contract getContract(String issuerId, String contractId);
    void addAddendum(String issuerId, String contractId, Addendum addendum);
}
