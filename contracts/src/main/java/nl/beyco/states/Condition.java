package nl.beyco.states;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.corda.core.serialization.ConstructorForDeserialization;
import net.corda.core.serialization.CordaSerializable;
import nl.beyco.helpers.LocalDateTimeDeserializer;
import nl.beyco.helpers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@CordaSerializable
public class Condition {
    private String id;
    private String type;
    private String status;
    private String title;
    private String value;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdAt;
    private String negotiationId;

    public Condition() {

    }

    @ConstructorForDeserialization
    public Condition(String id, String type, String status, String title, String value,
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
