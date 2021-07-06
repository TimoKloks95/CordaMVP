package nl.beyco.webserver.dto;

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

    @NotNull(message = "createdAt is mandatory")
    private LocalDateTime createdAt;

    @NotNull(message = "buyerSignedAt is mandatory")
    private LocalDateTime buyerSignedAt;

    @NotNull(message = "sellerSignedAt is mandatory")
    private LocalDateTime sellerSignedAt;

    @Valid
    private List<Condition> conditions;

    public Addendum(String id, LocalDateTime createdAt, LocalDateTime buyerSignedAt, LocalDateTime sellerSignedAt, List<Condition> conditions) {
        this.id = id;
        this.createdAt = createdAt;
        this.buyerSignedAt = buyerSignedAt;
        this.sellerSignedAt = sellerSignedAt;
        this.conditions = conditions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
