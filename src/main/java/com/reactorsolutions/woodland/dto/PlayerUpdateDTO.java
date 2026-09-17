package com.reactorsolutions.woodland.dto;

public class PlayerUpdateDTO {
    private String displayName;
    private String alias;
    private Long expectedVersion;

    public PlayerUpdateDTO() {}

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public Long getExpectedVersion() { return expectedVersion; }
    public void setExpectedVersion(Long expectedVersion) { this.expectedVersion = expectedVersion; }
}
