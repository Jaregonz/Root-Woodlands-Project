package com.reactorsolutions.woodland.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RemoveTokenAction extends Action {
    @NotNull
    private String clearingId;

    @Valid
    @NotNull
    private TokenPayload token;

    public String getClearingId() { return clearingId; }
    public void setClearingId(String clearingId) { this.clearingId = clearingId; }

    public TokenPayload getToken() { return token; }
    public void setToken(TokenPayload token) { this.token = token; }

    public static class TokenPayload {
        @NotNull
        @Pattern(regexp = "^(WOOD|SYMPATHY)$", message = "El tipo de token debe ser WOOD o SYMPATHY")
        private String tokenType;

        @Min(1)
        @Max(100)
        private int quantity;

        public String getTokenType() { return tokenType; }
        public void setTokenType(String tokenType) { this.tokenType = tokenType; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
}
