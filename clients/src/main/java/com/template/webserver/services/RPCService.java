package com.template.webserver.services;

import net.corda.core.messaging.CordaRPCOps;

public interface RPCService {
    void verbindMetBlockchain();
    CordaRPCOps getProxy();
}
