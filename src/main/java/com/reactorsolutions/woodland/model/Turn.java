package com.reactorsolutions.woodland.model;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "turns")
public class Turn {

    @Id
    private String id;

    @NotNull
    private String gameId;

    @NotNull
    private Integer sequence;

    @NotNull
    private String factionCode;

    @NotNull
    private Instant occurredAt;

    @Valid
    @NotNull
    private List<Action> actions = new ArrayList<>();

    @Valid
    @NotNull
    private List<ControlSnapshot> controlAfter = new ArrayList<>();

    private Instant createdAt;
    private Instant updatedAt;

    @Version
    private Long version;

    public static class ControlSnapshot {

        @NotNull
        private String clearingId;

        private String factionCode;

        public String getClearingId() {
            return clearingId;
        }

        public void setClearingId(String clearingId) {
            this.clearingId = clearingId;
        }

        public String getFactionCode() {
            return factionCode;
        }

        public void setFactionCode(String factionCode) {
            this.factionCode = factionCode;
        }
    }

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

    public List<ControlSnapshot> getControlAfter() {
        return controlAfter;
    }

    public void setControlAfter(List<ControlSnapshot> controlAfter) {
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
