package com.kosign.redoapi.service.language;

import com.kosign.redoapi.payload.languages.CreateLanguage;
import com.kosign.redoapi.payload.languages.LanguagesCriteria;
import com.kosign.redoapi.payload.languages.UpdateLanguageRequest;
import org.springframework.data.domain.Pageable;

public interface LanguageService {
    Object getLanguage(LanguagesCriteria criteria, Pageable pageable) throws Throwable;
    void createLanguage(CreateLanguage payload);
    Object getLanguageById(Long langCd);
    void enableLanguage(String langCd) throws Throwable;
    void disableLanguage(String langCd) throws Throwable;
    void updateLanguages(String langCode, UpdateLanguageRequest payload) throws Throwable;
}
