package com.template.webserver.business;

import net.corda.core.messaging.CordaRPCOps;

public interface RPCService {
    void verbindMetBlockchain();
    CordaRPCOps getProxy();
}
