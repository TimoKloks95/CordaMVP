package com.template.webserver.business.impl;

import com.template.webserver.business.exceptions.BeycoConnectionException;
import com.template.webserver.config.ClientConfiguration;
import com.template.webserver.business.RPCService;
import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.client.rpc.RPCException;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class RPCServiceImpl implements AutoCloseable, RPCService {
    private static final Logger log = LogManager.getLogger(ContractServiceImpl.class);
    private ClientConfiguration clientConfiguration;
    private CordaRPCConnection rpcConnection;
    private CordaRPCClient rpcClient;
    private CordaRPCOps proxy;

    public RPCServiceImpl(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    @PostConstruct
    @Override
    public void verbindMetBlockchain() {
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