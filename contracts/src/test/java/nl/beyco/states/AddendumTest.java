package nl.beyco.states;

import net.corda.core.contracts.UniqueIdentifier;
import nl.beyco.TestData;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddendumTest {

    @Test
    public void getLinearId() {
        Addendum testAddendum = TestData.getValidAddendum();
        UniqueIdentifier expected = new UniqueIdentifier(testAddendum.getId());
        assertEquals(expected.getExternalId(), testAddendum.getLinearId().getExternalId());
    }
}