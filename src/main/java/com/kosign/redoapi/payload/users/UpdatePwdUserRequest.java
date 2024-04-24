package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UpdatePwdUserRequest(
        @JsonProperty("new_usr_pwd")
        @NotBlank(message = "New password is required")
        String newUserPassword,
        @JsonProperty("curr_usr_pwd")
        String currentUserPassword

) {
}
