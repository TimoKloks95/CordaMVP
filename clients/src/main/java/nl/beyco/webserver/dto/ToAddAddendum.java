package nl.beyco.webserver.dto;

import net.corda.core.serialization.ConstructorForDeserialization;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ToAddAddendum extends Addendum {
    @NotBlank(message = "IssuerId is mandatory")
    private String issuerId;
    public ToAddAddendum() {

    }

    @ConstructorForDeserialization
    public ToAddAddendum(String issuerId, String id, LocalDateTime createdAt, LocalDateTime buyerSignedAt, LocalDateTime sellerSignedAt, List<AddendumCondition> conditions) {
        super(id, createdAt, buyerSignedAt, sellerSignedAt, conditions);
        this.issuerId = issuerId;
    }

    public String getIssuerId() {
        return issuerId;
    }
}
