package com.reactorsolutions.woodland.dto;

import com.reactorsolutions.woodland.model.enums.GameStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

public record GameSearchCriteriaDTO(
        GameStatus status,
        String playerId,
        String factionCode,
        String winnerFactionCode,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        Instant startedFrom,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        Instant startedTo,

        Integer minFinalScore
) {
}