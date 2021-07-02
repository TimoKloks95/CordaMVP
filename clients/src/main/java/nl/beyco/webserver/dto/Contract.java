package nl.beyco.webserver.dto;

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
    private List<Condition> conditions;

    @Valid
    private List<Coffee> coffees;

    @Valid
    private List<Addendum> addenda;

    public Contract(String id, String sellerId, String buyerId, String offerId, String sellerSignedAt,
                    String buyerSignedAt, List<Condition> conditions, List<Coffee> coffees,
                    List<Addendum> addenda) {
        this.id = id;
        this.sellerId = sellerId;
        this.buyerId = buyerId;
        this.offerId = offerId;
        this.sellerSignedAt = sellerSignedAt;
        this.buyerSignedAt = buyerSignedAt;
        this.conditions = conditions;
        this.coffees = coffees;
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

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }

    public void setCoffees(List<Coffee> coffees) {
        this.coffees = coffees;
    }

    public List<Addendum> getAddenda() {
        return addenda;
    }

    public void setAddenda(List<Addendum> addenda) {
        this.addenda = addenda;
    }
}
