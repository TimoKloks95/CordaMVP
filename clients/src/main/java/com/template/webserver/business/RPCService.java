package com.template.webserver.business;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.core.messaging.CordaRPCOps;

public interface RPCService {
    void connectToBlockchainNode();
    CordaRPCOps getProxy();
    void setRpcClient(CordaRPCClient client);
}
