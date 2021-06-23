package com.template.webserver.services;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.utilities.NetworkHostAndPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class RPCService implements AutoCloseable {
    private final String host;
    private final String username;
    private final String password;
    private final int rpcPort;

    private CordaRPCConnection rpcConnection;
    private CordaRPCOps proxy;

    public RPCService(@Value("${config.rpc.host}") String host,
                      @Value("${config.rpc.username}") String username,
                      @Value("${config.rpc.password}") String password,
                      @Value("${config.rpc.port}") int rpcPort) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.rpcPort = rpcPort;
    }

    @PostConstruct
    public void verbindMetBlockchain() {
        NetworkHostAndPort rpcAddress = new NetworkHostAndPort(host, rpcPort);
        CordaRPCClient rpcClient = new CordaRPCClient(rpcAddress);
        rpcConnection = rpcClient.start(username, password);
        this.proxy = rpcConnection.getProxy();
    }

    @PreDestroy
    @Override
    public void close() {
        rpcConnection.notifyServerAndClose();
    }

    public CordaRPCOps getProxy() {
        return this.proxy;
    }
}