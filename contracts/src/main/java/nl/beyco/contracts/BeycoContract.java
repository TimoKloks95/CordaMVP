package nl.beyco.contracts;

import net.corda.core.contracts.CommandData;
import net.corda.core.contracts.CommandWithParties;
import net.corda.core.contracts.Contract;
import net.corda.core.transactions.LedgerTransaction;
import nl.beyco.states.*;

import java.time.LocalDateTime;
import java.util.List;

import static net.corda.core.contracts.ContractsDSL.requireThat;

public class BeycoContract implements Contract {
    public static final String ID = "BeycoContract";

    @Override
    public void verify(LedgerTransaction tx) {
        for(CommandWithParties<CommandData> commands : tx.getCommands()) {
            if(commands.getValue() instanceof Commands.Save) {
                verifySaveContract(tx);
            }
            else if(commands.getValue() instanceof Commands.Add) {
                verifyAddAddendumToContract(tx);
            }
        }
    }

    private void verifySaveContract(LedgerTransaction tx) {
        BeycoContractState contract = tx.outputsOfType(BeycoContractState.class).get(0);
        requireThat(require -> {
            require.using("No inputs should be consumed when saving a contract.", tx.getInputStates().size() == 0);
            return null;
        });
        validateBeycoContract(contract);
    }

    private void verifyAddAddendumToContract(LedgerTransaction tx) {
        BeycoContractState outputContract = tx.outputsOfType(BeycoContractState.class).get(0);
        BeycoContractState inputContract = tx.inputsOfType(BeycoContractState.class).get(0);
        validateBeycoContract(outputContract);
        inputContractEqualsOutputContract(inputContract, outputContract);
    }

    private void inputContractEqualsOutputContract(BeycoContractState inputContract, BeycoContractState outputContract) {
        requireThat(require -> {
            require.using("Input contract should be the same as the output contract.", inputContract.equals(outputContract));
           return null;
        });
        inputCoffeesEqualsOutputCoffees(inputContract.getCoffees(), outputContract.getCoffees());
        inputConditionsEqualsOutputConditions(inputContract.getConditions(), outputContract.getConditions());
        inputAddendaEqualsOutputAddendaExceptForNewlyAdded(inputContract.getAddenda(), outputContract.getAddenda());
    }

    private void inputAddendaEqualsOutputAddendaExceptForNewlyAdded(List<Addendum> inputAddenda, List<Addendum> outputAddenda) {
        if(inputAddenda != null) {
            requireThat(require -> {
                require.using("The output contract should have one more addendum than the input contract.", inputAddenda.size() == outputAddenda.size() + 1);
               return null;
            });
            if(inputAddenda.size() > 0) {
                for(int i=0; i<inputAddenda.size(); i++) {
                    Addendum inputAddendum = inputAddenda.get(i);
                    for(int j=0; j<outputAddenda.size(); i++) {
                        Addendum outputAddendum = outputAddenda.get(j);
                        if(inputAddendum.getId().equals(outputAddendum.getId())) {
                            requireThat(require -> {
                                require.using("The existing addenda of the input contract should be equal to the existing addenda in the output contract.", inputAddendum.equals(outputAddendum));
                                return null;
                            });
                        }
                        inputConditionsEqualsOutputConditions(inputAddendum.getConditions(), outputAddendum.getConditions());
                    }
                }
            }
        }
    }

    private void inputConditionsEqualsOutputConditions(List<? extends Condition> inputConditions, List<? extends Condition> outputConditions) {
        requireThat(require -> {
            require.using("There should be an equal amount of input and output conditions", inputConditions.size() == outputConditions.size());
            return null;
        });
        for(int i=0; i<inputConditions.size(); i++) {
            Condition inputCondition = inputConditions.get(i);
            for(int j=0; j<outputConditions.size(); i++) {
                Condition outputCondition = outputConditions.get(j);
                if(inputCondition.getId().equals(outputCondition.getId())) {
                    requireThat(require -> {
                        require.using("The conditions of the input contract must be equal to the conditions of the output contract.", inputCondition.equals(outputCondition));
                        return null;
                    });
                }
            }
        }
    }

    private void inputCoffeesEqualsOutputCoffees(List<Coffee> inputCoffees, List<Coffee> outputCoffees) {
        requireThat(require -> {
            require.using("There should be an equal amount of input and output coffees", inputCoffees.size() == outputCoffees.size());
           return null;
        });
        for(int i=0; i<inputCoffees.size(); i++) {
            Coffee inputCoffee = inputCoffees.get(i);
            for(int j=0; j<outputCoffees.size(); i++) {
                Coffee outputCoffee = outputCoffees.get(j);
                if(inputCoffee.getId().equals(outputCoffee.getId())) {
                    requireThat(require -> {
                       require.using("The coffees of the input contract must be equal to the coffees of the output contract.", inputCoffee.equals(outputCoffee));
                       return null;
                    });
                }
            }
        }
    }

    private void validateBeycoContract(BeycoContractState contract) {
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
            require.using("Seller has not signed the contract. Both parties have to sign the contract.", contract.getSellerSignedAt() != null);
            require.using("Buyer has not signed the contract. Both parties have to sign the contract.", contract.getBuyerSignedAt() != null);
            require.using("Seller signed can't be after current datetime.", contract.getSellerSignedAt().isBefore(LocalDateTime.now()));
            require.using("Buyer signed can't be after current datetime.", contract.getBuyerSignedAt().isBefore(LocalDateTime.now()));
            require.using("Contract has to specify at least one coffee", contract.getCoffees().size() > 0);
            require.using("Contract has to specify at least one condition", contract.getConditions().size() > 0);
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
            require.using("Minimal screen size can't be negative or zero.", coffee.getMinScreenSize() > 0);
            require.using("Maximal screen size can't be negative or zero.", coffee.getMaxScreenSize() > 0);
            require.using("Coffee quantity can't be negative or zero.", coffee.getQuantity() > 0);
            require.using("Coffee cuppingscore can't be negative or zero.", coffee.getCuppingScore() > 0);
           return null;
        });
    }

    private void validateAddendumAttributes(Addendum addendum) {
        requireThat(require -> {
            require.using("Addendum id can't be empty or null.", addendum.getId() != null && !addendum.getId().isEmpty());
            require.using("Created at can't be after current datetime.", addendum.getCreatedAt().isBefore(LocalDateTime.now()));
            require.using("Buyer signed can't be after current datetime.", addendum.getBuyerSignedAt().isBefore(LocalDateTime.now()));
            require.using("Seller signed can't be after current datetime.", addendum.getSellerSignedAt().isBefore(LocalDateTime.now()));
            require.using("Addendum has to specify at least one condition.", addendum.getConditions().size() > 0);
            return null;
        });
        for(Condition condition : addendum.getConditions()) {
            validateConditionAttributes(condition);
        }
    }

    private void validateConditionAttributes(Condition condition) {
        requireThat(require -> {
           require.using("Condition id can't be empty or null.", condition.getId() != null && !condition.getId().isEmpty());
            require.using("Condition type can't be empty or null.", condition.getType() != null && !condition.getType().isEmpty());
            require.using("Condition status can't be empty or null.", condition.getStatus() != null && !condition.getStatus().isEmpty());
            require.using("Condition title can't be empty or null.", condition.getTitle() != null && !condition.getTitle().isEmpty());
            require.using("Condition value can't be empty or null.", condition.getValue() != null && !condition.getValue().isEmpty());
            require.using("Condition negotiation id can't be empty or null.", condition.getNegotiationId() != null && !condition.getNegotiationId().isEmpty());
           return null;
        });
    }

    public interface Commands extends CommandData {
        class Save implements Commands{}
        class Add implements Commands{}
    }
}
