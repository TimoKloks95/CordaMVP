package com.template.webserver.services.impl;

import com.template.webserver.config.ClientConfiguration;
import com.template.webserver.services.RPCService;
import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class RPCServiceImpl implements AutoCloseable, RPCService {
    private ClientConfiguration clientConfiguration;

    private CordaRPCConnection rpcConnection;
    private CordaRPCOps proxy;

    public RPCServiceImpl(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    @PostConstruct
    @Override
    public void verbindMetBlockchain() {
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(clientConfiguration.getHost(), clientConfiguration.getPort());
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        this.rpcConnection = rpcClient.start(clientConfiguration.getUsername(), clientConfiguration.getPassword());
        this.proxy = rpcConnection.getProxy();
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
}