package com.kosign.redoapi.payload.project;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProjectNameRequest(

        @JsonProperty("name")
        @NotBlank(message = "Project name cannot be null")
        @Size(max = 20, message = "Project name length must be less than or equal to 20 characters")
        String projectName

) {
}
