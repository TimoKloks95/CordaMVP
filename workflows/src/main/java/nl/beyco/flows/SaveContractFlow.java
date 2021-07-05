package nl.beyco.flows;

import co.paralleluniverse.fibers.Suspendable;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.flows.*;
import net.corda.core.identity.Party;
import net.corda.core.node.services.Vault;
import net.corda.core.node.services.vault.QueryCriteria;
import net.corda.core.transactions.SignedTransaction;
import net.corda.core.utilities.ProgressTracker;
import nl.beyco.states.BeycoContractState;
import sun.security.x509.UniqueIdentity;

import java.util.Collections;

@InitiatingFlow
@StartableByRPC
public class SaveContractFlow extends FlowLogic<Boolean> {
    private final ProgressTracker progressTracker = new ProgressTracker();
    @Override
    public ProgressTracker getProgressTracker() {
        return progressTracker;
    }

    private String issuerId;
    private String contractJson;
    private String coffeeJson;
    private String conditionsJson;
    private String addendaJson;
    private Party beyco;

    public SaveContractFlow(String issuerId, String contractJson, String coffeeJson, String conditionsJson,
                            String addendaJson) {
        this.issuerId = issuerId;
        this.contractJson = contractJson;
        this.coffeeJson = coffeeJson;
        this.conditionsJson = conditionsJson;
        this.addendaJson = addendaJson;
    }

    @Suspendable
    @Override
    public Boolean call() throws FlowException {
        // Get current node & notary
        beyco = getOurIdentity();
        final Party notary = getServiceHub().getNetworkMapCache().getNotaryIdentities().get(0);

        if(contractAlreadyExistsInVault()) {
            throw new FlowException("The contract that you tried to save already exists.");
        }

        // Build output

        // Create transactionbuilder

        // Verify output

        //
        return subFlow(null);
    }

    private boolean contractAlreadyExistsInVault() {
        QueryCriteria.LinearStateQueryCriteria linearStateQueryCriteria = new QueryCriteria.LinearStateQueryCriteria()
                .withExternalId(Collections.singletonList(issuerId));
        QueryCriteria.VaultQueryCriteria criteria = new QueryCriteria.VaultQueryCriteria(Vault.StateStatus.UNCONSUMED);
        Vault.Page<BeycoContractState> results = getServiceHub().getVaultService()
                .queryBy(BeycoContractState.class, criteria.and(linearStateQueryCriteria));
        return results.getStates().size() != 0;
    }
}
