package com.reactorsolutions.woodland.dto;

import java.util.List;

public class ClearingDTO {

    private String id;
    private String mapCode;
    private String name;
    private String suit;
    private Integer buildingSlots;
    private List<String> adjacentClearingIds;

    public ClearingDTO() {
    }

    public ClearingDTO(String id, String mapCode, String name, String suit, Integer buildingSlots, List<String> adjacentClearingIds) {
        this.id = id;
        this.mapCode = mapCode;
        this.name = name;
        this.suit = suit;
        this.buildingSlots = buildingSlots;
        this.adjacentClearingIds = adjacentClearingIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Integer getBuildingSlots() {
        return buildingSlots;
    }

    public void setBuildingSlots(Integer buildingSlots) {
        this.buildingSlots = buildingSlots;
    }

    public List<String> getAdjacentClearingIds() {
        return adjacentClearingIds;
    }

    public void setAdjacentClearingIds(List<String> adjacentClearingIds) {
        this.adjacentClearingIds = adjacentClearingIds;
    }
}
