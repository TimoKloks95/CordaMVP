package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.utilities.ProgressTracker;
import nl.beyco.states.BeycoContractState;

@InitiatingFlow
@StartableByRPC
public class GetContractFlow extends FlowLogic<BeycoContractState> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    public GetContractFlow() {

    }

    @Suspendable
    @Override
    public BeycoContractState call() throws FlowException {
        return null;
    }
}
