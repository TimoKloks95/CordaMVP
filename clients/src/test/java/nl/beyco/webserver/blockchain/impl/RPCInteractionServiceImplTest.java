package nl.beyco.webserver.blockchain.impl;

import net.corda.core.messaging.CordaRPCOps;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.blockchain.BeycoProxy;
import nl.beyco.webserver.blockchain.RPCInteractionService;
import nl.beyco.webserver.business.exceptions.BeycoFlowException;
import nl.beyco.webserver.helpers.impl.BeycoObjectManipulatorImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RPCInteractionServiceImplTest {
    @TestConfiguration
    static class RPCInteractionServiceImplTestContextConfiguration {
        @Bean
        public RPCInteractionServiceImpl rpcInteractionService() {
            return new RPCInteractionServiceImpl();
        }
    }

    @Autowired
    @InjectMocks
    private RPCInteractionServiceImpl rpcInteractionService;

    @MockBean
    private BeycoProxyImpl beycoProxy;

    @Test
    public void startSaveContractFlowSuccess() throws InterruptedException, ExecutionException {
        doNothing().when(beycoProxy).startSaveContractFlow(isA(String.class), isA(String.class));
        rpcInteractionService.startSaveContractFlow("1", "Test");
    }

    @Test(expected = BeycoFlowException.class)
    public void startSaveContractFlowFail() throws InterruptedException, ExecutionException {
        doThrow(new InterruptedException()).when(beycoProxy).startSaveContractFlow(isA(String.class), isA(String.class));
        rpcInteractionService.startSaveContractFlow("1", "Test");
    }


    @Test
    public void startGetContractFlowSuccess() throws InterruptedException, ExecutionException {
        ContractJsonWithAddendaJson expected = new ContractJsonWithAddendaJson("contract", new String[] {"addendum1", "addendum2"});
        when(beycoProxy.startGetContractFlow(isA(String.class), isA(String.class)))
                .thenReturn(expected);

        ContractJsonWithAddendaJson actual = rpcInteractionService.startGetContractFlow("1", "1");
        assertEquals(expected.getContractJson(), actual.getContractJson());
        assertEquals(expected.getAddendaJson()[0], actual.getAddendaJson()[0]);
        assertEquals(expected.getAddendaJson()[1], actual.getAddendaJson()[1]);
    }

    @Test(expected = BeycoFlowException.class)
    public void startGetContractFlowFail() throws InterruptedException, ExecutionException {
        doThrow(new InterruptedException()).when(beycoProxy).startGetContractFlow(isA(String.class), isA(String.class));
        ContractJsonWithAddendaJson actual = rpcInteractionService.startGetContractFlow("1", "1");
    }

    @Test
    public void startAddAddendumFlowSuccess() throws InterruptedException, ExecutionException {
        doNothing().when(beycoProxy).startAddAddendumFlow(isA(String.class), isA(String.class), isA(String.class));
        rpcInteractionService.startAddAddendumFlow("1", "1", "Test");
    }

    @Test(expected = BeycoFlowException.class)
    public void startAddAddendumFlowFail() throws InterruptedException, ExecutionException {
        doThrow(new InterruptedException()).when(beycoProxy).startAddAddendumFlow(isA(String.class), isA(String.class), isA(String.class));
        rpcInteractionService.startAddAddendumFlow("1", "1", "Test");
    }
}