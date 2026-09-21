package com.reactorsolutions.woodland.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GameDTO {
    private String id;
    private String name;
    private String status;
    private List<ParticipantDTO> participants;
    private String winnerFactionCode;
    private Instant createdAt;
    private Instant startedAt;
    private Instant finishedAt;
    private Instant cancelledAt;
    private Instant updatedAt;
    private Long version;

    public GameDTO() {
    }

    public GameDTO(String id, String name, String status, List<ParticipantDTO> participants, String winnerFactionCode, Instant startedAt, Instant createdAt, Instant finishedAt, Instant cancelledAt, Instant updatedAt, Long version) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.participants = participants;
        this.winnerFactionCode = winnerFactionCode;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.cancelledAt = cancelledAt;
        this.finishedAt = finishedAt;
        this.updatedAt = updatedAt;
        this.version = version;
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

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Instant getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(Instant cancelledAt) {
        this.cancelledAt = cancelledAt;
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
