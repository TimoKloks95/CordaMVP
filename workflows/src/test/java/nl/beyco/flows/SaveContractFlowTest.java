package nl.beyco.flows;

import net.corda.core.flows.FlowException;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.testing.node.*;
import nl.beyco.TestData;
import nl.beyco.contracts.BeycoContract;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.BeycoContractState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class SaveContractFlowTest {
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
    public void saveContractFlowSucceeds() throws InterruptedException, ExecutionException {
        SaveContractFlow flow = new SaveContractFlow("1", TestData.getContractJson());
        Future<SignedTransaction> future = mockNode.startFlow(flow);
        network.runNetwork();

        assertEquals(0, future.get().getTx().getInputs().size());

        QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria().withStatus(Vault.StateStatus.UNCONSUMED);
        BeycoContractState state = mockNode.getServices().getVaultService()
                .queryBy(BeycoContractState.class,inputCriteria).getStates().get(0).getState().getData();

        assertEquals("1", state.getId());
        assertEquals("1", state.getSellerId());
        assertEquals("2", state.getBuyerId());
        assertEquals("1", state.getOfferId());
        assertEquals(LocalDateTime.of(2021, 5, 11, 10, 00), state.getSellerSignedAt());
        assertEquals(LocalDateTime.of(2021, 5, 11, 10, 00), state.getBuyerSignedAt());
        assertEquals(1, state.getConditions().size());
        assertEquals(1, state.getCoffees().size());

    }

    @Test(expected = FlowException.class)
    public void saveContractFlowFailsBecauseOfParseError() {
        SaveContractFlow flow = new SaveContractFlow("1", TestData.getInvalidJson());
        mockNode.startFlow(flow);
        network.runNetwork();
    }

    @Test(expected = FlowException.class)
    public void saveContractFlowFailsBecauseIssuerIsNotSellerOrBuyer() {
        String notMatchingIssuerId = "5983";
        SaveContractFlow flow = new SaveContractFlow(notMatchingIssuerId, TestData.getContractJson());
        mockNode.startFlow(flow);
        network.runNetwork();
    }

    @Test(expected = FlowException.class)
    public void saveContractFlowFailsBecauseContractAlreadyExistsInVault() {
        SaveContractFlow flow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(flow);
        network.runNetwork();
        mockNode.startFlow(flow);
    }
}
