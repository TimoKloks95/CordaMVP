package nl.beyco.webserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;
import nl.beyco.webserver.helpers.BeycoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Addendum {
    @Autowired
    private BeycoSerializer serializer;

    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "sellerId is mandatory")
    private String sellerId;

    @NotBlank(message = "buyerId is mandatory")
    private String buyerId;

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
    public Addendum(String id, String sellerId, String buyerId, LocalDateTime createdAt, LocalDateTime buyerSignedAt, LocalDateTime sellerSignedAt, List<Coffee> coffees, List<Condition> conditions) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.createdAt = createdAt;
        this.buyerSignedAt = buyerSignedAt;
        this.sellerSignedAt = sellerSignedAt;
        this.coffees = coffees;
        this.conditions = conditions;
    }

    public String getId() {
        return id;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public void setId(String id) {
        this.id = id;
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

    public String toJson() {
        return serializer.toJson(this);
    }
}
