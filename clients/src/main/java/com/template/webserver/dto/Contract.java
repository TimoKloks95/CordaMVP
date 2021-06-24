package com.template.webserver.dto;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Contract {
    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "sellerId is mandatory")
    private String sellerId;

    @NotBlank(message = "buyerId is mandatory")
    private String buyerId;

    @NotBlank(message = "offerId is mandatory")
    private String offerId;

    @NotBlank(message = "sellerSignedAt is mandatory")
    private String sellerSignedAt;

    @NotBlank(message = "buyerSignedAt is mandatory")
    private String buyerSignedAt;

    @Valid
    private List<Conditie> condities;

    @Valid
    private List<Koffie> koffies;

    @Valid
    private List<Addendum> addenda;

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
