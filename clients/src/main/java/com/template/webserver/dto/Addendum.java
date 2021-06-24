package com.template.webserver.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Addendum {
    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "createdAt is mandatory")
    private String createdAt;

    @NotBlank(message = "buyerSignedAt is mandatory")
    private String buyerSignedAt;

    @NotBlank(message = "sellerSignedAt is mandatory")
    private String sellerSignedAt;

    private List<Conditie> condities;

    public Addendum(String id, String createdAt, String buyerSignedAt, String sellerSignedAt, List<Conditie> condities) {
        this.id = id;
        this.createdAt = createdAt;
        this.buyerSignedAt = buyerSignedAt;
        this.sellerSignedAt = sellerSignedAt;
        this.condities = condities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getBuyerSignedAt() {
        return buyerSignedAt;
    }

    public void setBuyerSignedAt(String buyerSignedAt) {
        this.buyerSignedAt = buyerSignedAt;
    }

    public String getSellerSignedAt() {
        return sellerSignedAt;
    }

    public void setSellerSignedAt(String sellerSignedAt) {
        this.sellerSignedAt = sellerSignedAt;
    }

    public List<Conditie> getCondities() {
        return condities;
    }

    public void setCondities(List<Conditie> condities) {
        this.condities = condities;
    }
}
