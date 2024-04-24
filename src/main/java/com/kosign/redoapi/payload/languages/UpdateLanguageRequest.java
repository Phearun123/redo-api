package com.kosign.redoapi.payload.languages;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record UpdateLanguageRequest(
//        @JsonProperty("lang_cd")
//        @NotNull(message = "Lang code cannot be null")
//        String langCd,
        @JsonProperty("name")
        @NotNull(message = "Name cannot be null")
        String name
) {
}
