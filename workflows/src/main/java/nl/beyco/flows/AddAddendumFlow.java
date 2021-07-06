package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.utilities.ProgressTracker;

@InitiatingFlow
@StartableByRPC
public class AddAddendumFlow extends FlowLogic<Boolean> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    public AddAddendumFlow() {

    }

    @Suspendable
    @Override
    public Boolean call() throws FlowException {

        return true;
    }
}
