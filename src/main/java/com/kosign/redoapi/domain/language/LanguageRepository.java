package com.kosign.redoapi.domain.language;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, String>, JpaSpecificationExecutor<Language> {

    @Query("select l from Language l where l.langCd = ?1")
    Optional<Language> findLangCd(String landCd);

    @Query("select l from Language l where l.langName = ?1")
    Optional<Language> findLangName(String landName);


}
