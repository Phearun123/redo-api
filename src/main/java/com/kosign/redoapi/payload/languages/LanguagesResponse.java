package com.kosign.redoapi.payload.languages;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude
public class LanguagesResponse {

    private String langCd;
    private String name;
    private String regiDtm;
    private String sts;

    @Builder
    public LanguagesResponse(String langCd, String name, String regiDtm, String sts) {
        this.langCd = langCd;
        this.name = name;
        this.regiDtm = regiDtm;
        this.sts = sts;
    }
}
