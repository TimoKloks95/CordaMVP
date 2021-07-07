package nl.beyco.webserver.dto;

import net.corda.core.serialization.ConstructorForDeserialization;

import java.time.LocalDateTime;

public class AddendumCondition extends Condition {
    public AddendumCondition() {

    }

    @ConstructorForDeserialization
    public AddendumCondition(String id, String type, String status, String title, String value,
                             LocalDateTime createdAt, String negotiationId) {
        super(id, type, status, title, value, createdAt, negotiationId);
    }
}
