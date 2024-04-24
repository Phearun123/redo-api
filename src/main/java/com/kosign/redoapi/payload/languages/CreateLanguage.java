package com.kosign.redoapi.payload.languages;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record CreateLanguage(
        @JsonProperty("lang_cd")
        @NotBlank(message = "need language code")
        String languageCode,
        @JsonProperty("name")
        @NotBlank(message = "need language name")
        String name
) {
}
