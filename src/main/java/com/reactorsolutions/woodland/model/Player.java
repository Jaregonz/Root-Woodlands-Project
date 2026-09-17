package com.reactorsolutions.woodland.model;

import org.springframework.data.annotation.*;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Document(collection = "players")
public class Player implements Persistable<String> {
    @Id
    @MongoId(FieldType.STRING)
    private String id;
    private String displayName;
    private String alias;

    @Indexed(unique = true)
    private String aliasNormalized;

    private boolean active;

    @CreatedDate
    @Field("createdAt")
    private Instant createdAt;

    @LastModifiedDate
    @Field("updatedAt")
    private Instant updatedAt;

    @Version
    @Field(targetType = FieldType.INT64)
    private Long version;


    public Player(String id, String displayName, String alias, String aliasNormalized, Instant createdAt, Instant updatedAt, Long version) {
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

    @Override
    public boolean isNew() {
        return this.createdAt == null;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
