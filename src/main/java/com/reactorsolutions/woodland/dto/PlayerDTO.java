package com.reactorsolutions.woodland.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class PlayerDTO {
    private String id;
    private String displayName;
    private String alias;
    private String aliasNormalized;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant updatedAt;

    private Long version;

    public PlayerDTO(String id, String displayName, String alias, String aliasNormalized, Instant createdAt, Instant updatedAt, Long version) {
        this.id = id;
        this.displayName = displayName;
        this.alias = alias;
        this.aliasNormalized = aliasNormalized;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.version = version;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAliasNormalized() {
        return aliasNormalized;
    }

    public void setAliasNormalized(String aliasNormalized) {
        this.aliasNormalized = aliasNormalized;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
