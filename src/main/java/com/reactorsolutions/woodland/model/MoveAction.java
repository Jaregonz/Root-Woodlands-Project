package com.reactorsolutions.woodland.model;

import com.reactorsolutions.woodland.model.enums.ActionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MoveAction extends Action {

    @Valid
    @NotNull
    private MovePayload move;

    public MovePayload getMove() {
        return move;
    }

    public void setMove(MovePayload move) {
        this.move = move;
    }

    @Override
    public ActionType getType() {
        return ActionType.MOVE;
    }

    public static class MovePayload {
        @NotNull
        private String fromClearingId;

        @NotNull
        private String toClearingId;

        @Min(1)
        @Max(100)
        private int warriors;

        public String getFromClearingId() {
            return fromClearingId;
        }

        public void setFromClearingId(String fromClearingId) {
            this.fromClearingId = fromClearingId;
        }

        public String getToClearingId() {
            return toClearingId;
        }

        public void setToClearingId(String toClearingId) {
            this.toClearingId = toClearingId;
        }

        public int getWarriors() {
            return warriors;
        }

        public void setWarriors(int warriors) {
            this.warriors = warriors;
        }
    }
}
