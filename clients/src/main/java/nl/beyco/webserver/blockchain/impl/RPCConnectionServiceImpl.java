package nl.beyco.webserver.blockchain.impl;

import nl.beyco.webserver.blockchain.RPCConnectionService;
import nl.beyco.webserver.business.exceptions.BeycoConnectionException;
import nl.beyco.webserver.config.ClientConfiguration;
import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.client.rpc.RPCException;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class RPCConnectionServiceImpl implements AutoCloseable, RPCConnectionService {
    @Autowired
    private ClientConfiguration clientConfiguration;
    private static final Logger log = LogManager.getLogger(RPCConnectionServiceImpl.class);

    private CordaRPCConnection rpcConnection;
    private CordaRPCClient rpcClient;
    private CordaRPCOps proxy;


    @PostConstruct
    @Override
    public void connectToBlockchainNode() {
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(clientConfiguration.getHost(), Integer.parseInt(clientConfiguration.getPort()));
        rpcClient = new CordaRPCClient(rpcAddress);
        try {
            this.rpcConnection = rpcClient.start(clientConfiguration.getUsername(), clientConfiguration.getPassword());
            this.proxy = rpcConnection.getProxy();
        } catch(RPCException e) {
            log.error("Something went wrong trying to connect to the corda blockchain node.", e);
            throw new BeycoConnectionException("Something went wrong trying to connect to the corda blockchain node.", e);
        }
    }

    @PreDestroy
    @Override
    public void close() {
        rpcConnection.notifyServerAndClose();
    }

    @Override
    public CordaRPCOps getProxy() {
        return this.proxy;
    }

    @Override
    public void setRpcClient(CordaRPCClient client) {
        this.rpcClient = client;
    }
}