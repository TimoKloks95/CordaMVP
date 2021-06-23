package com.template.webserver.dto;

public class Conditie {
    private String id;
    private String type;
    private String status;
    private String title;
    private String createdAt;
    private String negotiationId;

    public Conditie() {

    }

    public Conditie(String id, String type, String status, String title, String createdAt, String negotiationId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.title = title;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNegotiationId() {
        return negotiationId;
    }

    public void setNegotiationId(String negotiationId) {
        this.negotiationId = negotiationId;
    }
}
