package nl.beyco.flows;

import net.corda.core.identity.CordaX500Name;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.testing.node.*;
import nl.beyco.TestData;
import nl.beyco.states.Addendum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Future;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AddAddendumFlowTest {
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
    public void addAddendumSucceeds() {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        AddAddendumFlow addAddendumFlow = new AddAddendumFlow("1", "1", TestData.getAddendumJson());
        mockNode.startFlow(addAddendumFlow);
        network.runNetwork();

        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList("1"));
        QueryCriteria inputCriteria = new QueryCriteria.VaultQueryCriteria().withStatus(Vault.StateStatus.UNCONSUMED);
        Addendum state = mockNode.getServices().getVaultService()
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

    @Test
    public void addAddendumFlowFailsBecauseOfParseError() {
        AddAddendumFlow flow = new AddAddendumFlow("1", "1", TestData.getInvalidJson());
        Future<SignedTransaction> future = mockNode.startFlow(flow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }

    @Test
    public void addAddendumFlowFailsBecauseContractDoesntExist() {
        AddAddendumFlow flow = new AddAddendumFlow("1", "1", TestData.getAddendumJson());
        Future<SignedTransaction> future = mockNode.startFlow(flow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }

    @Test
    public void addAddendumFlowFailsBecauseAddendumAlreadyExists() {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        AddAddendumFlow addAddendumFlow = new AddAddendumFlow("1", "1", TestData.getAddendumJson());
        mockNode.startFlow(addAddendumFlow);
        network.runNetwork();

        AddAddendumFlow identicalFlow = new AddAddendumFlow("1", "1", TestData.getAddendumJson());

        Future<SignedTransaction> future = mockNode.startFlow(identicalFlow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }

    @Test
    public void addAddendumFlowFailsBecauseIssuerIsNotSellerOrBuyerAddendum() {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        AddAddendumFlow addAddendumFlow = new AddAddendumFlow("500", "1", TestData.getAddendumJson());
        Future<SignedTransaction> future = mockNode.startFlow(addAddendumFlow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }

    @Test
    public void addAddendumFlowFailsBecauseAddendumParticipantsDifferFromContractParticipants() {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        AddAddendumFlow addAddendumFlow = new AddAddendumFlow("1", "1", TestData.getAddendumJsonWithDifferentParticipants());
        Future<SignedTransaction> future = mockNode.startFlow(addAddendumFlow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }

    @Test
    public void addAddendumFlowFailsBecauseAddendumIsSignedBeforeContract() {
        SaveContractFlow saveContractFlow = new SaveContractFlow("1", TestData.getContractJson());
        mockNode.startFlow(saveContractFlow);
        network.runNetwork();

        AddAddendumFlow addAddendumFlow = new AddAddendumFlow("1", "1", TestData.getAddendumJsonWithSignedTimeBeforeContract());
        Future<SignedTransaction> future = mockNode.startFlow(addAddendumFlow);
        try {
            network.runNetwork();
            future.get();
            fail("Expected exception was not thrown.");
        } catch(Exception e) {
        }
    }
}
