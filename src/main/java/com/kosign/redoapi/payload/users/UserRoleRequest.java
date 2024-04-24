package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kosign.redoapi.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;

public record UserRoleRequest (
        @JsonProperty("role")
        @NotBlank(message = "role cannot be null")
        AuthProvider role

) {
}
