package nl.beyco.states;

import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;

import java.time.LocalDateTime;

@CordaSerializable
public class ConditionState {
    private final String id;
    private final String type;
    private final String status;
    private final String title;
    private final String value;
    private final LocalDateTime createdAt;
    private final String negotiationId;

    @ConstructorForDeserialization
    public ConditionState(String id, String type, String status, String title, String value,
                          LocalDateTime createdAt, String negotiationId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.title = title;
        this.value = value;
        this.createdAt = createdAt;
        this.negotiationId = negotiationId;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getNegotiationId() {
        return negotiationId;
    }
}
