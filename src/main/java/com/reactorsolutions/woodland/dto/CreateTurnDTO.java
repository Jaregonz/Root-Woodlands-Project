package com.reactorsolutions.woodland.dto;

import com.reactorsolutions.woodland.model.Action;
import com.reactorsolutions.woodland.model.Turn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CreateTurnDTO {
    @NotNull
    private Long expectedGameVersion;
    @NotNull
    private String factionCode;
    @NotNull
    private Instant occurredAt;
    @Valid
    @NotNull
    private List<Action> actions = new ArrayList<>();
    @Valid
    @NotNull
    private List<Turn.ControlSnapshot> controlAfter = new ArrayList<>();

    public Long getExpectedGameVersion() {
        return expectedGameVersion;
    }

    public void setExpectedGameVersion(Long expectedGameVersion) {
        this.expectedGameVersion = expectedGameVersion;
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
}
