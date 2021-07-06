package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.flows.FlowException;
import net.corda.core.flows.FlowLogic;
import net.corda.core.flows.InitiatingFlow;
import net.corda.core.flows.StartableByRPC;
import net.corda.core.identity.Party;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.utilities.ProgressTracker;

@InitiatingFlow
@StartableByRPC
public class AddAddendumFlow extends FlowLogic<SignedTransaction> {
    private String issuerId;
    private String contractId;
    private String addendumJson;
    private Party node;

    private final ProgressTracker progressTracker = new ProgressTracker();
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    public AddAddendumFlow(String issuerId, String contractId, String addendumJson) {
        this.issuerId = issuerId;
        this.contractId = contractId;
        this.addendumJson = addendumJson;
    }

    @Suspendable
    @Override
    public SignedTransaction call() throws FlowException {
        // Get current node & notary
        node = getOurIdentity();
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);


        return subFlow(null);
    }
}
