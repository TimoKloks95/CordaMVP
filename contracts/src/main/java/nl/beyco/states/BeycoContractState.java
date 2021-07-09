package nl.beyco.states;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.contracts.BelongsToContract;
import net.corda.core.contracts.ContractState;
import net.corda.core.contracts.LinearState;
import net.corda.core.contracts.UniqueIdentifier;
import net.corda.core.identity.AbstractParty;
import net.corda.core.identity.Party;
import net.corda.core.serialization.ConstructorForDeserialization;
import nl.beyco.contracts.BeycoContract;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@BelongsToContract(BeycoContract.class)
public class BeycoContractState implements ContractState, LinearState {
    private String id;
    private String sellerId;
    private String buyerId;
    private String offerId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sellerSignedAt;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime buyerSignedAt;
    private List<Coffee> coffees;
    private List<Condition> conditions;
    private Party node;

    public BeycoContractState() {

    }

    @ConstructorForDeserialization
    public BeycoContractState(String id, String sellerId, String buyerId, String offerId,
                              LocalDateTime sellerSignedAt, LocalDateTime buyerSignedAt, List<Coffee> coffees,
                              List<Condition> conditions) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.offerId = offerId;
        this.sellerSignedAt = sellerSignedAt;
        this.buyerSignedAt = buyerSignedAt;
        this.coffees = coffees;
        this.conditions = conditions;
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

    public void setNode(Party node) {
        this.node = node;
    }

    @NotNull
    @Override
    public List<AbstractParty> getParticipants() {
        return Collections.singletonList(node);
    }

    @NotNull
    @Override
    public UniqueIdentifier getLinearId() {
        return new UniqueIdentifier(id);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        BeycoContractState other = (BeycoContractState) obj;

        if(!Objects.deepEquals(
                new String[]{id, sellerId, buyerId, offerId, String.valueOf(sellerSignedAt), String.valueOf(buyerSignedAt)},
                new String[]{other.id, other.sellerId, other.buyerId, other.offerId, String.valueOf(other.sellerSignedAt), String.valueOf(other.buyerSignedAt)}
        )) {
            return false;
        }
        return true;
    }
}
