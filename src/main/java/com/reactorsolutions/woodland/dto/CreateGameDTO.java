package com.reactorsolutions.woodland.dto;

import java.util.List;

public class CreateGameDTO {
    private String name;
    private String mapCode;
    private List<String> clearingIds;
    private List<CreateParticipantDTO> participants;

    public CreateGameDTO() {
    }

    public CreateGameDTO(String mapCode, String name, List<String> clearingIds, List<CreateParticipantDTO> participants) {
        this.mapCode = mapCode;
        this.name = name;
        this.clearingIds = clearingIds;
        this.participants = participants;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateParticipantDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<CreateParticipantDTO> participants) {
        this.participants = participants;
    }

    public List<String> getClearingIds() {
        return clearingIds;
    }

    public void setClearingIds(List<String> clearingIds) {
        this.clearingIds = clearingIds;
    }
}

