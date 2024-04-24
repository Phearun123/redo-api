package com.kosign.redoapi.payload.languages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.redoapi.common.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude
public class LanguagesMainResponse {

    @JsonProperty("data")
    private List<LanguagesResponse> languagesResponses;

    private Pagination pagination;

    @Builder
    public LanguagesMainResponse(List<LanguagesResponse> languagesResponses, Page<?> page) {
        this.languagesResponses = languagesResponses;
        this.pagination = new Pagination(page);
    }
}
