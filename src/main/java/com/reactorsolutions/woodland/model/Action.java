package com.reactorsolutions.woodland.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.reactorsolutions.woodland.model.enums.ActionType;
import jakarta.validation.constraints.NotNull;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MoveAction.class, name = "MOVE"),
        @JsonSubTypes.Type(value = BattleAction.class, name = "BATTLE"),
        @JsonSubTypes.Type(value = BuildAction.class, name = "BUILD"),
        @JsonSubTypes.Type(value = RecruitAction.class, name = "RECRUIT"),
        @JsonSubTypes.Type(value = CraftAction.class, name = "CRAFT"),
        @JsonSubTypes.Type(value = ScorePointsAction.class, name = "SCORE_POINTS"),
        @JsonSubTypes.Type(value = PlaceTokenAction.class, name = "PLACE_TOKEN"),
        @JsonSubTypes.Type(value = RemoveTokenAction.class, name = "REMOVE_TOKEN")
})
public abstract class Action {
    private String actionId;

    @NotNull
    private ActionType type;

    public String getActionId() { return actionId; }
    public void setActionId(String actionId) { this.actionId = actionId; }

    public ActionType getType() { return type; }
    public void setType(ActionType type) { this.type = type; }
}
