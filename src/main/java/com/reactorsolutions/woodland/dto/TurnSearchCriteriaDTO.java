package com.reactorsolutions.woodland.dto;

import com.reactorsolutions.woodland.model.enums.ActionType;

public record TurnSearchCriteriaDTO(
        String factionCode,
        ActionType actionType
) { }
