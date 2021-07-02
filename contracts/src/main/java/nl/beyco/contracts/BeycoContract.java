package nl.beyco.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.CommandWithParties;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import nl.beyco.states.Addendum;
import nl.beyco.states.BeycoContractState;
import nl.beyco.states.Coffee;
import nl.beyco.states.Condition;

import java.time.LocalDateTime;

import static net.corda.core.contracts.ContractsDSL.requireThat;

public class BeycoContract implements Contract {
    public static final String ID = "BeycoContract";

    @Override
    public void verify(LedgerTransaction tx) {
        for(CommandWithParties<CommandData> commands : tx.getCommands()) {
            if(commands.getValue() instanceof Commands.Save) {
                verifySaveContract(tx.outputsOfType(BeycoContractState.class).get(0));
            }
            else if(commands.getValue() instanceof Commands.Add) {
                verifyAddAddendumToContract(tx.outputsOfType(BeycoContractState.class).get(0));
            }
        }
    }

    private void verifySaveContract(BeycoContractState contract) {
        validateBeycoContractStateAttributes(contract);
        for(Coffee coffee : contract.getCoffees()) {
            validateCoffeeAttributes(coffee);
        }
        for(Condition condition : contract.getConditions()) {
            validateConditionAttributes(condition);
        }
        for(Addendum addendum : contract.getAddenda()) {
            validateAddendumAttributes(addendum);
        }
    }

    private void validateBeycoContractStateAttributes(BeycoContractState contract) {
        requireThat(require -> {
            require.using("Contract id can't be empty or null.", contract.getId() != null && !contract.getId().isEmpty());
            require.using("Seller id can't be empty or null.", contract.getSellerId() != null && !contract.getSellerId().isEmpty());
            require.using("Buyer id can't be empty or null.", contract.getBuyerId() != null && !contract.getBuyerId().isEmpty());
            require.using("Offer id can't be empty or null.", contract.getOfferId() != null && !contract.getOfferId().isEmpty());
            require.using("Seller signed can't be null.", contract.getSellerSignedAt() != null);
            require.using("Buyer signed can't be null.", contract.getBuyerSignedAt() != null);
            require.using("Seller signed can't be after current datetime.", contract.getSellerSignedAt().isAfter(LocalDateTime.now()));
            require.using("Buyer signed can't be after current datetime.", contract.getBuyerSignedAt().isAfter(LocalDateTime.now()));
            require.using("Contract has to specify at least one coffee", contract.getCoffees().size() > 0);
            require.using("Contract has to specify at least one condition", contract.getConditions().size() > 0);
            require.using("Issuer of the contract has to be equal the buyer or the seller", contract.getIssuerId().equals(contract.getSellerId()) || contract.getIssuerId().equals(contract.getBuyerId()));
            return null;
        });
    }

    private void validateCoffeeAttributes(Coffee coffee) {
        requireThat(require -> {
            require.using("Coffee id can't be empty or null.", coffee.getId() != null && !coffee.getId().isEmpty());
            require.using("Coffee country can't be empty or null.", coffee.getCountry() != null && !coffee.getCountry().isEmpty());
            require.using("Coffee region can't be empty or null.", coffee.getRegion() != null && !coffee.getRegion().isEmpty());
            require.using("Coffee unit can't be empty or null.", coffee.getUnit() != null && !coffee.getUnit().isEmpty());
            require.using("Coffee species can't be empty or null.", coffee.getSpecies() != null && !coffee.getSpecies().isEmpty());
            require.using("Coffee process can't be empty or null.", coffee.getProcess() != null && !coffee.getProcess().isEmpty());
            require.using("Coffee sector can't be empty or null.", coffee.getSector() != null && !coffee.getSector().isEmpty());
            require.using("Coffee quality segment can't be empty or null.", coffee.getQualitySegment() != null && !coffee.getQualitySegment().isEmpty());
            require.using("Minimal screen size can't be empty or null.", coffee.getMinScreenSize() != null && !coffee.getMinScreenSize().isEmpty());
            require.using("Maximal screen size can't be empty or null.", coffee.getMaxScreenSize() != null && !coffee.getMaxScreenSize().isEmpty());
            require.using("Coffee quantity can't be negative or zero.", coffee.getQuantity() > 0);
            require.using("Coffee cuppingscore can't be negative or zero.", coffee.getCuppingScore() > 0);
           return null;
        });
    }

    private void validateAddendumAttributes(Addendum addendum) {
        //TODO
    }

    private void validateConditionAttributes(Condition condition) {
        //TODO
    }

    private void verifyAddAddendumToContract(BeycoContractState contract) {
        //TODO
    }

    public interface Commands extends CommandData {
        class Save implements Commands{}
        class Add implements Commands{}
    }
}
