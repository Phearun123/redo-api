package com.kosign.redoapi.payload.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthRequest(

        @JsonProperty("usr_nm")
        String username,
        @JsonProperty("usr_pwd")
        String password

) {
}
