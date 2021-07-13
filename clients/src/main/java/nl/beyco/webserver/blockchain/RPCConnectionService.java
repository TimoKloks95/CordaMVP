package nl.beyco.webserver.blockchain;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.core.messaging.CordaRPCOps;

public interface RPCConnectionService {
    void connectToBlockchainNode();
    CordaRPCOps getProxy();
    void setRpcClient(CordaRPCClient client);
}
