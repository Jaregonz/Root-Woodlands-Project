package com.reactorsolutions.woodland.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RecruitAction extends Action {

    @NotNull
    private String clearingId;

    @Valid
    @NotNull
    private RecruitPayload recruit;

    public String getClearingId() { return clearingId; }
    public void setClearingId(String clearingId) { this.clearingId = clearingId; }

    public RecruitPayload getRecruit() { return recruit; }
    public void setRecruit(RecruitPayload recruit) { this.recruit = recruit; }

    public static class RecruitPayload {
        @Min(1)
        @Max(100)
        private int warriors;

        public int getWarriors() { return warriors; }
        public void setWarriors(int warriors) { this.warriors = warriors; }
    }
}
