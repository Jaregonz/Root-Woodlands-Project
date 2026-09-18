package com.reactorsolutions.woodland.dto;

import com.reactorsolutions.woodland.model.Participant;

import java.time.Instant;
import java.util.List;

public class GameDTO {
    private String id;
    private String name;
    private String status;
    private List<ParticipantDTO> participants;
    private String winnerFactionCode;
    private Instant createdAt;
    private Instant updatedAt;

    public GameDTO() {
    }

    public GameDTO(String id, String name, String status, List<ParticipantDTO> participants,
                           String winnerFactionCode, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.participants = participants;
        this.winnerFactionCode = winnerFactionCode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<ParticipantDTO> participants) {
        this.participants = participants;
    }

    public String getWinnerFactionCode() {
        return winnerFactionCode;
    }

    public void setWinnerFactionCode(String winnerFactionCode) {
        this.winnerFactionCode = winnerFactionCode;
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
}
