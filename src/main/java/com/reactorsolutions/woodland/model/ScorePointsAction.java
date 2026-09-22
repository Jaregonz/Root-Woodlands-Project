package com.reactorsolutions.woodland.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ScorePointsAction extends Action {

    @Valid
    @NotNull
    private ScorePayload score;

    public ScorePayload getScore() { return score; }
    public void setScore(ScorePayload score) { this.score = score; }

    public static class ScorePayload {
        @Min(1)
        @Max(100)
        private int points;

        @NotNull
        @Size(min = 1, max = 200)
        private String reason;

        public int getPoints() { return points; }
        public void setPoints(int points) { this.points = points; }

        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
}
