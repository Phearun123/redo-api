package com.kosign.redoapi.domain.language;

import com.kosign.redoapi.domain.CreatableEntity;
import com.kosign.redoapi.enums.StatusActive;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "TB_LANG")
@Entity
@NoArgsConstructor
public class Language extends CreatableEntity {

    @Id
    @Column(name = "lang_cd", length = 2,nullable = false,unique = true)
    private String langCd;

    @Column(name = "nm", length = 20, unique = true, nullable = false)
    private String langName;

    @Column(name = "sts")
    @Convert(converter = StatusActive.Converter.class)
    private StatusActive status;
    @Builder
    public Language(String langCd, String langName, StatusActive status) {
        this.langCd = langCd;
        this.langName = langName;
        this.status = status;
    }
}
