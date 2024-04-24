package com.kosign.redoapi.payload.languages;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LanguagesCriteria {
    private String searchValue;
    private Integer pageNumber;
    private Integer pageSize;

    @Builder
    public LanguagesCriteria(String searchValue, Integer pageNumber, Integer pageSize) {
        this.searchValue = searchValue;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
