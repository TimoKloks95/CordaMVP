package nl.beyco.flows;

import net.corda.core.identity.CordaX500Name;
import net.corda.testing.node.*;
import nl.beyco.TestData;
import nl.beyco.states.ContractJsonWithAddendaJson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GetContractFlowTest {
    private MockNetwork network;
    private StartedMockNode mockNode;

    @Before
    public void setup() {
        network = new MockNetwork(new MockNetworkParameters().withCordappsForAllNodes(Arrays.asList(
                TestCordapp.findCordapp("nl.beyco.contracts"), TestCordapp.findCordapp("nl.beyco.flows")))
                .withNotarySpecs(Collections.singletonList(new MockNetworkNotarySpec(new CordaX500Name("Notary", "Amsterdam", "NL")))));
        mockNode = network.createNode(new MockNodeParameters());
        network.runNetwork();
    }

    @After
    public void tearDown() {
        network.stopNodes();
    }

    @Test
    public void getContractFlowSucceeds() throws InterruptedException, ExecutionException {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        GetContractFlow flow = new GetContractFlow("1", "1");
        Future<ContractJsonWithAddendaJson> promise = mockNode.startFlow(flow);
        ContractJsonWithAddendaJson result = promise.get();
        network.runNetwork();

        assertEquals(0, result.getAddendaJson().length);
        assertEquals(false, result.getContractJson().isEmpty());
    }

    @Test
    public void getContractFlowFailsBecauseContractDoesntExist() {
        GetContractFlow flow = new GetContractFlow("1", "1");
        Future<ContractJsonWithAddendaJson> future = mockNode.startFlow(flow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }

    @Test
    public void getContractFlowFailsBecauseIssuerIsNotSellerOrBuyer() {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        String notMatchingIssuerId = "5554";
        GetContractFlow flow = new GetContractFlow(notMatchingIssuerId, "1");
        Future<ContractJsonWithAddendaJson> future = mockNode.startFlow(flow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch (Exception e) {
        }
    }
}