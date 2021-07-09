package nl.beyco;

import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.testing.node.*;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.BeycoContractState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

public class SaveContractFlowTest {
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
    public void saveContractTest() throws InterruptedException, ExecutionException {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getTestContract());

        Future<SignedTransaction> futureSave = a.startFlow(saveContractFlow);
        network.runNetwork();
        SignedTransaction signedTransaction = futureSave.get();

        assertEquals(0, signedTransaction.getTx().getInputs().size());

        QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria().withStatus(Vault.StateStatus.UNCONSUMED);
        BeycoContractState state = a.getServices().getVaultService()
                .queryBy(BeycoContractState.class,inputCriteria).getStates().get(0).getState().getData();

        assertEquals("1", state.getId());
        assertEquals("1", state.getOfferId());
        assertEquals("1", state.getSellerId());
        assertEquals("2", state.getBuyerId());

    }
}
