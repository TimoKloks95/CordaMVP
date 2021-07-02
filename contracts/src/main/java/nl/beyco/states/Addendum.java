package nl.beyco.states;

import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;

import java.time.LocalDateTime;
import java.util.List;

@CordaSerializable
public class Addendum {
    private final String id;
    private final LocalDateTime createdAt;
    private final LocalDateTime buyerSignedAt;
    private final LocalDateTime sellerSignedAt;
    private final List<AddendumCondition> conditions;

    @ConstructorForDeserialization
    public Addendum(String id, LocalDateTime createdAt, LocalDateTime buyerSignedAt, LocalDateTime sellerSignedAt,
                    List<AddendumCondition> conditions) {
        this.id = id;
        this.createdAt = createdAt;
        this.buyerSignedAt = buyerSignedAt;
        this.sellerSignedAt = sellerSignedAt;
        this.conditions = conditions;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getBuyerSignedAt() {
        return buyerSignedAt;
    }

    public LocalDateTime getSellerSignedAt() {
        return sellerSignedAt;
    }

    public List<AddendumCondition> getConditions() {
        return conditions;
    }
}
