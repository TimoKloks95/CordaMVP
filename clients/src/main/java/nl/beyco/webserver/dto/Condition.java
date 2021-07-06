package nl.beyco.webserver.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Condition {
    @NotBlank(message = "id is mandatory")
    private String id;

    @NotBlank(message = "type is mandatory")
    private String type;

    @NotBlank(message = "status is mandatory")
    private String status;

    @NotBlank(message = "title is mandatory")
    private String title;

    @NotBlank(message = "value is mandatory")
    private String value;

    @NotNull(message = "createdAt is mandatory")
    private LocalDateTime createdAt;

    @NotBlank(message = "negotiationId is mandatory")
    private String negotiationId;

    public Condition(String id, String type, String status, String title, String value, LocalDateTime createdAt, String negotiationId) {
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

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(String negotiationId) {
        this.negotiationId = negotiationId;
    }
}
