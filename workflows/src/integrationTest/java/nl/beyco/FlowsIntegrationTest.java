package nl.beyco;

import net.corda.client.rpc.CordaRPCClient;
import net.corda.core.concurrent.CordaFuture;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.messaging.CordaRPCOps;
import net.corda.core.node.services.Vault;
import net.corda.testing.core.TestIdentity;
import net.corda.testing.driver.DriverParameters;
import net.corda.testing.driver.NodeHandle;
import net.corda.testing.driver.NodeParameters;
import net.corda.testing.node.User;
import nl.beyco.flows.AddAddendumFlow;
import nl.beyco.flows.GetContractFlow;
import nl.beyco.flows.SaveContractFlow;
import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;
import nl.beyco.states.ContractJsonWithAddendaJson;
import org.junit.Test;
import rx.Observable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;

import static net.corda.testing.core.ExpectKt.expect;
import static net.corda.testing.core.ExpectKt.expectEvents;
import static net.corda.testing.driver.Driver.driver;
import static org.junit.Assert.*;


public class FlowsIntegrationTest {
    private final TestIdentity testNode = new TestIdentity(new CordaX500Name("Progreso", "", "NL"));
    private final String USERNAME = "testusername";
    private final String PASSWORD = "testpassword";
    private final String PERMISSIONS = "ALL";
    private final String TEST_ISSUER = "1";
    private final String TEST_CONTRACT_ID = "1";

    @Test
    public void saveContractAddAddendumAndGetContractIntegrationTest() {
        driver(new DriverParameters().withIsDebug(true).withStartNodesInProcess(true), dsl -> {
            User beycoUser = new User(USERNAME, PASSWORD, new HashSet<>(Collections.singletonList(PERMISSIONS)));
            CordaFuture<NodeHandle> handleFuture = dsl.startNode(new NodeParameters()
                    .withProvidedName(testNode.getName())
                    .withRpcUsers(Collections.singletonList(beycoUser)));

            try {
                NodeHandle testHandle = handleFuture.get();

                CordaRPCClient testClient = new CordaRPCClient(testHandle.getRpcAddress());
                CordaRPCOps testProxy = testClient.start(USERNAME, PASSWORD).getProxy();

                Observable<Vault.Update<BeycoContractState>> beycoVaultContractUpdates = testProxy.vaultTrack(BeycoContractState.class).getUpdates();

                String contractJson = TestData.getContractJson();
                testProxy.startFlowDynamic(SaveContractFlow.class, TEST_ISSUER, contractJson).getReturnValue().get();

                @SuppressWarnings("unchecked")
                Class<Vault.Update<BeycoContractState>> contractVaultUpdateClass = (Class<Vault.Update<BeycoContractState>>)(Class<?>)Vault.Update.class;

                expectEvents(beycoVaultContractUpdates, true, () ->
                        expect(contractVaultUpdateClass, update -> true, update -> {
                            BeycoContractState addedState = update.getProduced().iterator().next().getState().getData();
                            assertEquals("1", addedState.getId());
                            assertEquals("1", addedState.getSellerId());
                            assertEquals("2", addedState.getBuyerId());
                            assertEquals("1", addedState.getOfferId());
                            assertEquals(LocalDateTime.of(2021, 5, 11, 10, 00), addedState.getSellerSignedAt());
                            assertEquals(LocalDateTime.of(2021, 5, 11, 10, 00), addedState.getBuyerSignedAt());
                            assertEquals(1, addedState.getConditions().size());
                            assertEquals(1, addedState.getCoffees().size());
                            return null;
                        })
                );

                Observable<Vault.Update<Addendum>> beycoVaultAddendumUpdates = testProxy.vaultTrack(Addendum.class).getUpdates();

                String addendumJson = TestData.getAddendumJson();
                testProxy.startFlowDynamic(AddAddendumFlow.class, TEST_ISSUER, TEST_CONTRACT_ID, addendumJson).getReturnValue().get();

                @SuppressWarnings("unchecked")
                Class<Vault.Update<Addendum>> addendumVaultUpdateClass = (Class<Vault.Update<Addendum>>)(Class<?>)Vault.Update.class;

                expectEvents(beycoVaultAddendumUpdates, true, () ->
                        expect(addendumVaultUpdateClass, update -> true, update -> {
                            Addendum addedState = update.getProduced().iterator().next().getState().getData();
                            assertEquals("1", addedState.getId());
                            assertEquals("1", addedState.getSellerId());
                            assertEquals("2", addedState.getBuyerId());
                            assertEquals(LocalDateTime.of(2021, 6, 15, 10, 00), addedState.getCreatedAt());
                            assertEquals(LocalDateTime.of(2021, 6, 14, 10, 00), addedState.getBuyerSignedAt());
                            assertEquals(LocalDateTime.of(2021, 6, 13, 10, 00), addedState.getSellerSignedAt());
                            assertEquals(1, addedState.getConditions().size());
                            assertEquals(1, addedState.getCoffees().size());
                            return null;
                        })
                );

                ContractJsonWithAddendaJson result = testProxy.startFlowDynamic(GetContractFlow.class, TEST_ISSUER, TEST_CONTRACT_ID).getReturnValue().get();
                assertNotNull(result.getContractJson());
                assertEquals(1, result.getAddendaJson().length);
            } catch (Exception e) {
                throw new RuntimeException("Caught exception during test: ", e);
            }
            return null;
        });
    }
}