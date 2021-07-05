package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.utilities.ProgressTracker;

@InitiatingFlow
@StartableByRPC
public class SaveContractFlow extends FlowLogic<Boolean> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    private Party beyco;

    public SaveContractFlow() {
        // Get current node
        beyco = getOurIdentity();
        // Get inputs

        // Build output

        // Create transactionbuilder

        // Verify output

        //

    }

    @Suspendable
    @Override
    public Boolean call() throws FlowException {
        return subFlow(null);
    }
}
