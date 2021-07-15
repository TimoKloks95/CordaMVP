package nl.beyco.webserver.business.impl;

import javafx.util.Pair;
import nl.beyco.states.ContractJsonWithAddendaJson;
import nl.beyco.webserver.TestData;
import nl.beyco.webserver.blockchain.RPCInteractionService;
import nl.beyco.webserver.business.ContractsService;
import nl.beyco.webserver.dto.Addendum;
import nl.beyco.webserver.dto.Contract;
import nl.beyco.webserver.helpers.BeycoSerializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class ContractServiceImplTest {
    @TestConfiguration
    static class ContractsServiceImplTestContextConfiguration {
        @Bean
        public ContractsService contractService() {
            return new ContractsServiceImpl();
        }
    }

    @Autowired
    @InjectMocks
    private ContractsServiceImpl contractsService;

    @MockBean
    private RPCInteractionService rpcInteractionService;

    @MockBean
    private BeycoSerializer beycoSerializer;

    @Test
    public void saveContractTest() {
        String testJson = "Test";
        String testIssuerId = "1";
        Contract testContract = TestData.getContractForTestJson();
        when(beycoSerializer.toJson(testContract))
                .thenReturn(testJson);
        doNothing().when(rpcInteractionService).startSaveContractFlow(testIssuerId, testJson);
        contractsService.saveContract(testIssuerId, testContract);
        verify(rpcInteractionService).startSaveContractFlow(testIssuerId, testJson);
    }

    @Test
    public void getContractTest() {
        String testIssuerId = "1";
        String testContractId = "1";
        ContractJsonWithAddendaJson testContractJsonWithAddendaJson = new ContractJsonWithAddendaJson("contractJson", new String[] {"addendum1"});
        Contract testContract = TestData.getContractForTestJson();
        Addendum testAddendum = TestData.getAddendumForTestJson();
        when(rpcInteractionService.startGetContractFlow(testIssuerId, testContractId))
                .thenReturn(testContractJsonWithAddendaJson);
        when(beycoSerializer.toObject(testContractJsonWithAddendaJson.getContractJson(), Contract.class))
                .thenReturn(testContract);
        when(beycoSerializer.toObject(testContractJsonWithAddendaJson.getAddendaJson()[0], Addendum.class))
                .thenReturn(testAddendum);

        Pair<Contract, List<Addendum>> actual = contractsService.getContract(testIssuerId, testContractId);

        assertEquals(1, actual.getValue().size());
        assertEquals(testAddendum, actual.getValue().get(0));
        assertEquals(testContract, actual.getKey());
    }

    @Test
    public void addAddendumTest() {
        String testJson = "Test";
        String testIssuerId = "1";
        String testContractId = "1";
        Addendum testAddendum = TestData.getAddendumForTestJson();
        when(beycoSerializer.toJson(testAddendum))
                .thenReturn(testJson);
        doNothing().when(rpcInteractionService).startAddAddendumFlow(testIssuerId, testContractId, testJson);
        contractsService.addAddendum(testIssuerId, testContractId, testAddendum);
        verify(rpcInteractionService).startAddAddendumFlow(testIssuerId, testContractId, testJson);
    }
}