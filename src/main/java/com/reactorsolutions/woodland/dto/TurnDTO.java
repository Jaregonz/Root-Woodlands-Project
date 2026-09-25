package com.reactorsolutions.woodland.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reactorsolutions.woodland.model.Action;
import com.reactorsolutions.woodland.model.Turn;

import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TurnDTO {
    private String id;
    private String gameId;
    private Integer sequence;
    private String factionCode;
    private Instant occurredAt;
    private List<Action> actions;
    private List<Turn.ControlSnapshot> controlAfter;
    private Instant createdAt;
    private Instant updatedAt;
    private Long version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getFactionCode() {
        return factionCode;
    }

    public void setFactionCode(String factionCode) {
        this.factionCode = factionCode;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(Instant occurredAt) {
        this.occurredAt = occurredAt;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    public List<Turn.ControlSnapshot> getControlAfter() {
        return controlAfter;
    }

    public void setControlAfter(List<Turn.ControlSnapshot> controlAfter) {
        this.controlAfter = controlAfter;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
