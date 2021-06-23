package com.template.webserver.dto;

import java.util.List;

public class Addendum {
    private String id, createdAt, buyerSignedAt, sellerSignedAt;
    private List<Conditie> condities;
    public Addendum() {

    }

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
