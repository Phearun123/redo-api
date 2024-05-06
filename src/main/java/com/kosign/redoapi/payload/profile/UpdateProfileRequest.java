package com.kosign.redoapi.payload.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UpdateProfileRequest(

        @JsonProperty("full_nm")
        @NotBlank(message = "Full name can not be null")
        String fullName,
        @JsonProperty("usr_prof_img")
        String userProfileImag

) {
}
