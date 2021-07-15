package nl.beyco.webserver.blockchain.impl;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.client.rpc.RPCException;
import net.corda.core.messaging.CordaRPCOps;
import nl.beyco.webserver.blockchain.RPCConnectionService;
import nl.beyco.webserver.business.exceptions.BeycoConnectionException;
import nl.beyco.webserver.config.ClientConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RPCConnectionServiceImplTest {
    @TestConfiguration
    static class RPCConnectionServiceImplTestContextConfiguration {

        @Bean
        public RPCConnectionService rpcConnectionService() {
            RPCConnectionService rpcConnectionService = mock(RPCConnectionServiceImpl.class);
            CordaRPCClient rpcClient = mock(CordaRPCClient.class);
            CordaRPCConnection rpcConnection = mock(CordaRPCConnection.class);
            CordaRPCOps rpcOps = mock(CordaRPCOps.class);
            rpcConnectionService.setRpcClient(rpcClient);

            when(rpcClient.start(isA(String.class), isA(String.class)))
                    .thenReturn(rpcConnection);

            when(rpcConnection.getProxy()).thenReturn(rpcOps);
            return rpcConnectionService;
        }

        @Bean
        public ClientConfiguration clientConfiguration() {
            ClientConfiguration configuration = mock(ClientConfiguration.class);
            when(configuration.getHost()).thenReturn("testhost");
            when(configuration.getPort()).thenReturn("8080");
            when(configuration.getUsername()).thenReturn("testuser");
            when(configuration.getPassword()).thenReturn("testpass");
            return configuration;
        }
    }

    @Autowired
    private RPCConnectionService rpcService;

    @Test
    public void connectToBlockchain() {
        verify(rpcService).connectToBlockchainNode();
    }

}