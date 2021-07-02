package nl.beyco.states;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import nl.beyco.contracts.BeycoContract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@BelongsToContract(BeycoContract.class)
public class BeycoContractState implements ContractState {
    private final String id;
    private final String sellerId;
    private final String buyerId;
    private final String offerId;
    private final LocalDateTime sellerSignedAt;
    private final LocalDateTime buyerSignedAt;
    private final List<CoffeeState> coffees;
    private final List<ConditionState> conditions;
    private List<AddendumState> addenda;
    private final String issuerId;

    public BeycoContractState(String id, String sellerId, String buyerId, String offerId,
                              LocalDateTime sellerSignedAt, LocalDateTime buyerSignedAt,
                              List<CoffeeState> coffees, List<ConditionState> conditions,
                              List<AddendumState> addenda, String issuerId) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.offerId = offerId;
        this.sellerSignedAt = sellerSignedAt;
        this.buyerSignedAt = buyerSignedAt;
        this.coffees = coffees;
        this.conditions = conditions;
        this.addenda = addenda;
        this.issuerId = issuerId;
    }

    public String getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getOfferId() {
        return offerId;
    }

    public LocalDateTime getSellerSignedAt() {
        return sellerSignedAt;
    }

    public LocalDateTime getBuyerSignedAt() {
        return buyerSignedAt;
    }

    public List<CoffeeState> getCoffees() {
        return coffees;
    }

    public List<ConditionState> getConditions() {
        return conditions;
    }

    public List<AddendumState> getAddenda() {
        return addenda;
    }

    public void addAddendum(AddendumState addendum) {
        addenda.add(addendum);
    }

    public String getIssuerId() {
        return issuerId;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return Collections.emptyList();
    }
}
