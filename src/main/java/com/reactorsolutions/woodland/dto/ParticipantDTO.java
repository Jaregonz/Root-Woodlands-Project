package com.reactorsolutions.woodland.dto;

public record ParticipantDTO(
        String playerId,
        String displayNameSnapshot,
        String factionCode,
        Integer finalScore
) {}
