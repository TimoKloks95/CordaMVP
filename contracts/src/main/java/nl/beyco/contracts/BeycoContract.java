package nl.beyco.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.CommandWithParties;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import nl.beyco.states.BeycoContractState;

import static net.corda.core.contracts.ContractsDSL.requireThat;

public class BeycoContract implements Contract {
    public static final String ID = "BeycoContract";

    @Override
    public void verify(LedgerTransaction tx) {
        for(CommandWithParties<CommandData> commands : tx.getCommands()) {
            if(commands.getValue() instanceof Commands.Save) {
                verifySave(tx.outputsOfType(BeycoContractState.class).get(0));
            }
            else if(commands.getValue() instanceof Commands.Add) {
                verifyAdd(tx.outputsOfType(BeycoContractState.class).get(0));
            }
        }
    }

    private void verifySave(BeycoContractState contract) {
        //TODO
        requireThat(require -> {
            require.using("Contract id can't be empty or null.", contract.getId() != null && !contract.getId().isEmpty());
            return null;
        });
    }

    private void verifyAdd(BeycoContractState contract) {
        //TODO
    }

    public interface Commands extends CommandData {
        class Save implements Commands{}
        class Add implements Commands{}
    }
}
