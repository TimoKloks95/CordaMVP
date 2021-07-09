package nl.beyco.webserver.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Addendum {
    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "parentId is mandatory")
    private String parentId;

    @NotNull(message = "createdAt is mandatory")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @NotNull(message = "buyerSignedAt is mandatory")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime buyerSignedAt;

    @NotNull(message = "sellerSignedAt is mandatory")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime sellerSignedAt;

    @Valid
    private List<Coffee> coffees;

    @Valid
    private List<Condition> conditions;

    public Addendum() {

    }

    @ConstructorForDeserialization
    public Addendum(String id, String parentId, LocalDateTime createdAt, LocalDateTime buyerSignedAt, LocalDateTime sellerSignedAt, List<Coffee> coffees, List<Condition> conditions) {
        this.id = id;
        this.parentId = parentId;
        this.createdAt = createdAt;
        this.buyerSignedAt = buyerSignedAt;
        this.sellerSignedAt = sellerSignedAt;
        this.coffees = coffees;
        this.conditions = conditions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public void setCoffees(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getBuyerSignedAt() {
        return buyerSignedAt;
    }

    public void setBuyerSignedAt(LocalDateTime buyerSignedAt) {
        this.buyerSignedAt = buyerSignedAt;
    }

    public LocalDateTime getSellerSignedAt() {
        return sellerSignedAt;
    }

    public void setSellerSignedAt(LocalDateTime sellerSignedAt) {
        this.sellerSignedAt = sellerSignedAt;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
