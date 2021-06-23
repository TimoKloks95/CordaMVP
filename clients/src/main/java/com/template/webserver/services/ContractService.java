package com.template.webserver.services;

import com.template.webserver.dto.Addendum;
import com.template.webserver.dto.Contract;
import net.corda.core.messaging.CordaRPCOps;

public interface ContractService {
    Contract opslaanContract(String issuerId, Contract contract);
    Contract ophalenContract(String issuerId, String contractId);
    Contract toevoegenAddendum(String issuerId, String contractId, Addendum addendum);
}
