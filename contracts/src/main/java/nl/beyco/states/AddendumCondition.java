package nl.beyco.states;

import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;

import java.time.LocalDateTime;

@CordaSerializable
public class AddendumCondition extends Condition {
    public AddendumCondition() {

    }
    @ConstructorForDeserialization
    public AddendumCondition(String id, String type, String status, String title, String value,
                             LocalDateTime createdAt, String negotiationId) {
        super(id, type, status, title, value, createdAt, negotiationId);
    }

    public AddendumCondition copy() {
        return new AddendumCondition(this.getId(), this.getType(), this.getStatus(), this.getTitle(),
                this.getValue(), this.getCreatedAt(), this.getNegotiationId());
    }
}
