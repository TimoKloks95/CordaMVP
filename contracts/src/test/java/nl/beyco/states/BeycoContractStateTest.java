package nl.beyco.states;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.CordaX500Name;
import net.corda.core.identity.Party;
import net.corda.testing.node.MockNetwork;
import net.corda.testing.node.MockNetworkParameters;
import net.corda.testing.node.StartedMockNode;
import net.corda.testing.node.TestCordapp;
import nl.beyco.TestData;
import org.junit.Before;
import org.junit.Test;

import java.security.PublicKey;
import java.util.Arrays;

import static org.junit.Assert.*;

public class BeycoContractStateTest {

    @Test
    public void getLinearId() {
        BeycoContractState testContract = TestData.getValidContract();
        UniqueIdentifier expected = new UniqueIdentifier(testContract.getId());
        assertEquals(expected.getExternalId(), testContract.getLinearId().getExternalId());
    }
}