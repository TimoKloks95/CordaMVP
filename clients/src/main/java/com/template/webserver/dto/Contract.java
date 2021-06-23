package com.template.webserver.dto;

import java.util.List;

public class Contract {
    private String id;
    private String sellerId;
    private String buyerId;
    private String offerId;
    private String sellerSignedAt;
    private String buyerSignedAt;
    private List<Conditie> condities;
    private List<Koffie> koffies;
    private List<Addendum> addenda;

    public Contract() {

    }

    public Contract(String id, String sellerId, String buyerId, String offerId, String sellerSignedAt,
                    String buyerSignedAt, List<Conditie> condities, List<Koffie> koffies,
                    List<Addendum> addenda) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.offerId = offerId;
        this.sellerSignedAt = sellerSignedAt;
        this.buyerSignedAt = buyerSignedAt;
        this.condities = condities;
        this.koffies = koffies;
        this.addenda = addenda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getSellerSignedAt() {
        return sellerSignedAt;
    }

    public void setSellerSignedAt(String sellerSignedAt) {
        this.sellerSignedAt = sellerSignedAt;
    }

    public String getBuyerSignedAt() {
        return buyerSignedAt;
    }

    public void setBuyerSignedAt(String buyerSignedAt) {
        this.buyerSignedAt = buyerSignedAt;
    }

    public List<Conditie> getCondities() {
        return condities;
    }

    public void setCondities(List<Conditie> condities) {
        this.condities = condities;
    }

    public List<Koffie> getKoffies() {
        return koffies;
    }

    public void setKoffies(List<Koffie> koffies) {
        this.koffies = koffies;
    }

    public List<Addendum> getAddenda() {
        return addenda;
    }

    public void setAddenda(List<Addendum> addenda) {
        this.addenda = addenda;
    }
}
