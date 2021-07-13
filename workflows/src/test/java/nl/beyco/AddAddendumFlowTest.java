package nl.beyco;

import com.google.common.collect.ImmutableList;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.testing.node.*;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.Addendum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;


public class AddAddendumFlowTest {
    private MockNetwork network;
    private StartedMockNode a;
    private TestStateFactory stateFactory;

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
    public void jsonToAddendumError() {

    }

    @Test
    public void contractDoesntExist() {

    }

    @Test
    public void addendumAlreadyExists() {

    }

    @Test
    public void issuerNotSellerOrBuyerOfContract() {

    }

    @Test
    public void issuerNotSellerOrBuyerOfAddendum() {

    }

    @Test
    public void addendumSellerOrBuyerDifferentFromContractSellerOrBuyer() {

    }

    @Test
    public void addendumSignedTimeBeforeContractSignedTime() {

    }



    @Test
    public void addAddendumSuccess() throws InterruptedException, ExecutionException {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getTestContract());
        a.startFlow(saveContractFlow);
        network.runNetwork();

        AddAddendumFlow addAddendumFlow = new AddAddendumFlow("1", "1", TestData.getTestAddendum());
        Future<SignedTransaction> futureAdd = a.startFlow(addAddendumFlow);
        network.runNetwork();

        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList("1"));
        QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria().withStatus(Vault.StateStatus.UNCONSUMED);
        Addendum state = a.getServices().getVaultService()
                .queryBy(Addendum.class,inputCriteria.and(linearStateQueryCriteria)).getStates().get(0).getState().getData();

        assertEquals("1", state.getId());
        assertEquals("1", state.getSellerId());
        assertEquals("2", state.getBuyerId());
        assertEquals(LocalDateTime.of(2021, 06, 15, 10, 00), state.getCreatedAt());
        assertEquals(LocalDateTime.of(2021, 06, 14, 10, 00), state.getBuyerSignedAt());
        assertEquals(LocalDateTime.of(2021, 06, 13, 10, 00), state.getSellerSignedAt());
        assertEquals(1, state.getCoffees().size());
        assertEquals(1, state.getConditions().size());
    }
}
