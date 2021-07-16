package nl.beyco.flows;

import net.corda.core.flows.FlowException;
import net.corda.core.identity.CordaX500Name;
import net.corda.testing.node.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class GetContractFlowTest {
    private MockNetwork network;
    private StartedMockNode a;

    @Before
    public void setup() {
        network = new MockNetwork(new MockNetworkParameters().withCordappsForAllNodes(Arrays.asList(
                TestCordapp.findCordapp("nl.beyco.contracts"), TestCordapp.findCordapp("nl.beyco.flows")))
                .withNotarySpecs(Collections.singletonList(new MockNetworkNotarySpec(new CordaX500Name("Notary", "Amsterdam", "NL")))));
        a = network.createNode(new MockNodeParameters());
        network.runNetwork();
    }

    @After
    public void tearDown() {
        network.stopNodes();
    }

    @Test
    public void getContractFlowSucceeds() {

    }

    @Test(expected = FlowException.class)
    public void getContractFlowFailsBecauseContractDoesntExist() {

    }

    @Test(expected = FlowException.class)
    public void getContractFlowFailsBecauseIssuerIsNotSellerOrBuyer() {

    }
}