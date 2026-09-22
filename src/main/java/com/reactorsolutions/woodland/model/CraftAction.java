package com.reactorsolutions.woodland.model;

import com.reactorsolutions.woodland.model.enums.ActionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CraftAction extends Action {

    @Valid
    @NotNull
    private CraftPayload craft;

    public CraftPayload getCraft() {
        return craft;
    }

    public void setCraft(CraftPayload craft) {
        this.craft = craft;
    }

    @Override
    public ActionType getType() {
        return ActionType.CRAFT;
    }

    public static class CraftPayload {
        @NotNull
        @Size(min = 1, max = 50)
        private String itemCode;

        @Min(1)
        @Max(20)
        private int quantity;

        public String getItemCode() {
            return itemCode;
        }

        public void setItemCode(String itemCode) {
            this.itemCode = itemCode;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
