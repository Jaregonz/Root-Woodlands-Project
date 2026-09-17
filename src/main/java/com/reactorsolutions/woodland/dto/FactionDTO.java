package com.reactorsolutions.woodland.dto;

public class FactionDTO {
    private String id;
    private String displayName;
    private String description;
    private boolean enabled;

    public FactionDTO() {
    }

    public FactionDTO(String id, String displayName, String description, boolean enabled) {
        this.id = id;
        this.displayName = displayName;
        this.description = description;
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
