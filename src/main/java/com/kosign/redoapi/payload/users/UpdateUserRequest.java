package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kosign.redoapi.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
        @JsonProperty("full_nm")
        @NotBlank(message = "Full name can't be null")
        String fullName,
        @JsonProperty("role")
        @NotBlank(message = "Role can't be null")
        AuthProvider role

) {
}
