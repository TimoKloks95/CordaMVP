package nl.beyco.states;

import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import nl.beyco.contracts.BeycoContract;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@BelongsToContract(BeycoContract.class)
public class BeycoContractState implements ContractState {
    private final String id;
    private final String sellerId;
    private final String buyerId;
    private final String offerId;
    private final LocalDateTime sellerSignedAt;
    private final LocalDateTime buyerSignedAt;
    private final List<Coffee> coffees;
    private final List<Condition> conditions;
    private List<Addendum> addenda;
    private final String issuerId;
    private Party beyco;

    public BeycoContractState(String id, String sellerId, String buyerId, String offerId,
                              LocalDateTime sellerSignedAt, LocalDateTime buyerSignedAt,
                              List<Coffee> coffees, List<Condition> conditions,
                              List<Addendum> addenda, String issuerId, Party beyco) {
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
        this.beyco = beyco;
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

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public List<Addendum> getAddenda() {
        return addenda;
    }

    public Party getBeyco() {
        return beyco;
    }

    public void addAddendum(Addendum addendum) {
        addenda.add(addendum);
    }

    public String getIssuerId() {
        return issuerId;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return Arrays.asList(beyco);
    }
}
