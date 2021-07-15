package nl.beyco.contracts;

import net.corda.core.identity.CordaX500Name;
import net.corda.testing.core.TestIdentity;
import net.corda.testing.node.MockServices;
import nl.beyco.TestData;
import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;
import org.junit.Test;

import java.util.Collections;

import static net.corda.testing.node.NodeTestUtils.ledger;

public class BeycoContractTest {
    private final MockServices ledgerServices = new MockServices(Collections.singletonList("nl.beyco"));
    TestIdentity admin = new TestIdentity(new CordaX500Name("Beyco", "Testland", "NL"));

    @Test
    public void saveContractSucceeds() {
        BeycoContractState state = TestData.getValidContract();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
               tx.output(BeycoContract.ID, state);
               tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
               return tx.verifies();
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseOfInputState() {
        BeycoContractState state = TestData.getValidContract();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.input(BeycoContract.ID, state);
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("No inputs should be consumed when saving a contract.");
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseOfEmptyIdField() {
        BeycoContractState state = TestData.getInvalidContractWithEmptyIdField();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("Contract id can't be empty or null.");
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseSignedTimeIsAfterCurrentTime() {
        BeycoContractState state = TestData.getInvalidContractWithSignedTimeAfterCurrentTime();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("Seller signed can't be after current datetime.");
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseNoCoffeeIsProvided() {
        BeycoContractState state = TestData.getInvalidContractWithNoCoffee();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("Contract has to specify at least one coffee.");
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseNoConditionIsProvided() {
        BeycoContractState state = TestData.getInvalidContractWithNoCondition();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("Contract has to specify at least one condition.");
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseCoffeeIsInvalid() {
        BeycoContractState state = TestData.getInvalidContractWithInvalidCoffee();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("Maximal screen size should be between 8 and 20.");
            });
            return null;
        });
    }

    @Test
    public void saveContractFailsBecauseConditionIsInvalid() {
        BeycoContractState state = TestData.getInvalidContractWithInvalidCondition();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Save());
                return tx.failsWith("Condition id can't be empty or null.");
            });
            return null;
        });
    }

    @Test
    public void addAddendumSucceeds() {
        Addendum state = TestData.getValidAddendum();
        ledger(ledgerServices, l -> {
           l.transaction(tx -> {
               tx.output(BeycoContract.ID, state);
               tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
               return tx.verifies();
           });
           return null;
        });
    }

    @Test
    public void addAddendumFailsBecauseOfInputState() {
        Addendum state = TestData.getValidAddendum();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.input(BeycoContract.ID, state);
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
                return tx.failsWith("No inputs should be consumed when adding an addendum.");
            });
            return null;
        });
    }

    @Test
    public void addAddendumFailsBecauseOfEmptyIdField() {
        Addendum state = TestData.getInvalidAddendumWithEmptyIdField();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
                return tx.failsWith("Addendum id can't be empty or null.");
            });
            return null;
        });
    }

    @Test
    public void addAddendumFailsBecauseSignedTimeIsAfterCurrentTime() {
        Addendum state = TestData.getInvalidAddendumWithSignedTimeAfterCurrentTime();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
                return tx.failsWith("Created at can't be after current datetime.");
            });
            return null;
        });
    }

    @Test
    public void addAddendumFailsBecauseNoConditionIsProvided() {
        Addendum state = TestData.getInvalidAddendumWithNoCondition();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
                return tx.failsWith("Addendum has to specify at least one condition.");
            });
            return null;
        });
    }

    @Test
    public void addAddendumFailsBecauseCoffeeIsInvalid() {
        Addendum state = TestData.getInvalidAddendumWithInvalidCoffee();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
                return tx.failsWith("Maximal screen size should be between 8 and 20.");
            });
            return null;
        });
    }

    @Test
    public void addAddendumFailsBecauseConditionIsInvalid() {
        Addendum state = TestData.getInvalidAddendumWithInvalidCondition();
        ledger(ledgerServices, l -> {
            l.transaction(tx -> {
                tx.output(BeycoContract.ID, state);
                tx.command(admin.getPublicKey(), new BeycoContract.Commands.Add());
                return tx.failsWith("Condition id can't be empty or null.");
            });
            return null;
        });
    }

}