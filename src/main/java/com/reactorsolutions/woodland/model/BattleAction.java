package com.reactorsolutions.woodland.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BattleAction extends Action {

    @NotNull
    private String clearingId;

    @Valid
    @NotNull
    private BattlePayload battle;

    public String getClearingId() { return clearingId; }
    public void setClearingId(String clearingId) { this.clearingId = clearingId; }

    public BattlePayload getBattle() { return battle; }
    public void setBattle(BattlePayload battle) { this.battle = battle; }

    public static class BattlePayload {
        @NotNull
        private String defenderFactionCode;

        @Min(0)
        @Max(100)
        private int attackerLosses;

        @Min(0)
        @Max(100)
        private int defenderLosses;

        @Min(0)
        @Max(100)
        private int defenderBuildingsDestroyed;

        public String getDefenderFactionCode() { return defenderFactionCode; }
        public void setDefenderFactionCode(String defenderFactionCode) { this.defenderFactionCode = defenderFactionCode; }

        public int getAttackerLosses() { return attackerLosses; }
        public void setAttackerLosses(int attackerLosses) { this.attackerLosses = attackerLosses; }

        public int getDefenderLosses() { return defenderLosses; }
        public void setDefenderLosses(int defenderLosses) { this.defenderLosses = defenderLosses; }

        public int getDefenderBuildingsDestroyed() { return defenderBuildingsDestroyed; }
        public void setDefenderBuildingsDestroyed(int defenderBuildingsDestroyed) { this.defenderBuildingsDestroyed = defenderBuildingsDestroyed; }
    }
}
