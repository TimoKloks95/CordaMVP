package nl.beyco.contracts;

import net.corda.testing.node.MockServices;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BeycoContractTest {
    private final MockServices ledgerServices = new MockServices(Arrays.asList("nl.beyco"));

    @Test
    public void stringAttributeCannotBeNull() {
    }

    @Test
    public void stringAttributeCannotBeEmpty() {
    }

    @Test
    public void nestedStringAttributeCannotBeNull() {
    }

    @Test
    public void nestedStringAttributeCannotBeEmpty() {
    }

    @Test
    public void intAttributeCannotBeZero() {
    }

    @Test
    public void dateAttributeCannotBeAfterCurrentDateTime() {
    }

    @Test
    public void nestedDateAttributeCannotBeAfterCurrentDateTime() {
    }

    @Test
    public void addendumHasAtLeastOneCondition() {
    }

    @Test
    public void contractHasAtLeastOnCondition() {
    }

    @Test
    public void contractIncludesAddendumAfterAddingAddendum() {
    }

    @Test
    public void contractIssuerHasToBeEqualToSellerOrBuyer() {
    }
}