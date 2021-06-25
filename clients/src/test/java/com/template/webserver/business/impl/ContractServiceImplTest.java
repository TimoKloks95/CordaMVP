package com.template.webserver.business.impl;

import com.template.flows.OpslaanContractFlow;
import com.template.webserver.business.ContractService;
import com.template.webserver.business.RPCService;
import com.template.webserver.config.ClientConfiguration;
import com.template.webserver.dto.Contract;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaPropertyInitializerEvaluator;
import net.corda.client.rpc.CordaRPCClient;
import net.corda.client.rpc.CordaRPCConnection;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.transactions.SignedTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ContractServiceImplTest {
    @TestConfiguration
    static class ContractServiceImplTestContextConfiguration {
        @Bean
        public ContractService contractService() {
            return new ContractServiceImpl();
        }
    }

    @Autowired
    private ContractService contractService;

    @MockBean
    private RPCService rpcService;

    @Test
    public void opslaanContract() throws Exception {
//        //Arrange
//        Contract testContract = new Contract("1", "1", "2", "1", "12 mei 2021",
//                "12 mei 2021", null, null, null);
//        CordaRPCOps testProxy = mock(CordaRPCOps.class);
//        when(rpcService.getProxy()).thenReturn(testProxy);
//        when(testProxy.startTrackedFlowDynamic(OpslaanContractFlow.class, isA(String.class), isA(Contract.class)))
//        .thenReturn(null);
//
//        //Act
//        contractService.opslaanContract("1", testContract);
//
//        //Assert
//        verify(rpcService).getProxy();
//        verify(testProxy).startTrackedFlowDynamic(OpslaanContractFlow.class, isA(String.class), isA(Contract.class));

    }

    @Test
    public void ophalenContract() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void toevoegenAddendum() {
        //Arrange
        CordaRPCOps testProxy = mock(CordaRPCOps.class);

        //Act

        //Assert
    }
}