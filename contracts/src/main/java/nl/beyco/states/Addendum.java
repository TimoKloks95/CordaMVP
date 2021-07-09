package nl.beyco.states;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CordaSerializable
public class Addendum {
    private String id;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime buyerSignedAt;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sellerSignedAt;
    private List<AddendumCondition> conditions;

    public Addendum() {

    }

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

    public Addendum copy() {
        List<AddendumCondition> conditionsCopy = new ArrayList<>();
        for(AddendumCondition condition : conditions) {
            conditionsCopy.add(condition.copy());
        }
        return new Addendum(this.id, this.createdAt, this.buyerSignedAt, this.sellerSignedAt, conditionsCopy);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }

        Addendum other = (Addendum) obj;

        if(!Objects.deepEquals(
            new String[] {id, String.valueOf(createdAt), String.valueOf(buyerSignedAt), String.valueOf(sellerSignedAt)},
            new String[] {other.id, String.valueOf(other.createdAt), String.valueOf(other.buyerSignedAt), String.valueOf(other.sellerSignedAt)}
        )) {
            return false;
        }
        return true;
    }
}
