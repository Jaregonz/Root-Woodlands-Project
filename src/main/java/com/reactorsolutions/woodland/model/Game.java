package com.reactorsolutions.woodland.model;

import com.reactorsolutions.woodland.model.enums.GameStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Document(collection = "games")
public class Game {
    @Id
    private String id;
    private String name;
    private GameStatus status;
    private String mapCode;
    private List<String> clearingIds;
    private List<Participant> participants;
    private String winnerFactionCode;

    @CreatedDate
    private Instant createdAt;
    private Instant startedAt;
    private Instant finishedAt;

    @LastModifiedDate
    private Instant updatedAt;

    @Version
    private Long version;

    public Game(String id, String name, GameStatus status, String mapCode, List<String> clearingIds, List<Participant> participants, String winnerFactionCode, Instant createdAt, Instant startedAt, Instant finishedAt, Instant updatedAt, Long version) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.mapCode = mapCode;
        this.clearingIds = clearingIds;
        this.participants = participants;
        this.winnerFactionCode = winnerFactionCode;
        this.createdAt = createdAt;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.updatedAt = updatedAt;
        this.version = version;
    }

    public Game() {
    }

    public static Game createNew(String name, String mapCode, List<String> clearingIds, List<Participant> participants) {
        Game game = new Game();
        game.setId(UUID.randomUUID().toString());
        game.setName(name);
        game.setStatus(GameStatus.DRAFT);
        game.setMapCode(mapCode);
        game.setClearingIds(clearingIds);
        game.setParticipants(participants);
        return game;
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

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public List<String> getClearingIds() {
        return clearingIds;
    }

    public void setClearingIds(List<String> clearingIds) {
        this.clearingIds = clearingIds;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
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
