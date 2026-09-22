package com.reactorsolutions.woodland.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class BuildAction extends Action {

    @NotNull
    private String clearingId;

    @Valid
    @NotNull
    private BuildPayload build;

    public String getClearingId() { return clearingId; }
    public void setClearingId(String clearingId) { this.clearingId = clearingId; }

    public BuildPayload getBuild() { return build; }
    public void setBuild(BuildPayload build) { this.build = build; }

    public static class BuildPayload {
        @NotNull
        @Pattern(regexp = "^(WORKSHOP|SAWMILL|ROOST)$", message = "El tipo debe ser WORKSHOP, SAWMILL o ROOST")
        private String buildingType;

        public String getBuildingType() { return buildingType; }
        public void setBuildingType(String buildingType) { this.buildingType = buildingType; }
    }
}
