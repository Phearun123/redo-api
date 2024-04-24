package com.kosign.redoapi.payload.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kosign.redoapi.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
      @JsonProperty("usr_nm")
      @NotBlank(message = "Username can not be null")
      String userName,

      @JsonProperty("usr_pwd")
      @NotBlank(message = "User password can not be null")
      String userPassword,

      @JsonProperty("full_nm")
      @NotBlank(message = "User Fullname can not be null")
      String userFullName,

      @JsonProperty("role")
      @NotBlank(message = "Role can not be null")
      AuthProvider role

) {
}
