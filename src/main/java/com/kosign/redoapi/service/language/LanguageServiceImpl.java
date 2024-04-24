package com.kosign.redoapi.service.language;

import com.kosign.redoapi.common.api.StatusCode;
import com.kosign.redoapi.domain.language.Language;
import com.kosign.redoapi.domain.language.LanguageRepository;
import com.kosign.redoapi.enums.StatusActive;
import com.kosign.redoapi.exception.BusinessException;
import com.kosign.redoapi.payload.languages.*;
import com.kosign.redoapi.utils.BaseSpecification;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public Object getLanguage(LanguagesCriteria criteria, Pageable pageable) throws Throwable {

        Page<Language> languagePage = languageRepository.findAll((root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (criteria != null) {
                if (StringUtils.isNotBlank(criteria.getSearchValue())) {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.equal(root.get("status"), StatusActive.fromValue(criteria.getSearchValue())),
                            criteriaBuilder.like(root.get("langCd"), BaseSpecification.containLowerCase(criteria.getSearchValue())),
                            criteriaBuilder.like(root.get("langName"), BaseSpecification.containLowerCase(criteria.getSearchValue()))
                    ));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        var data = languagePage.stream().map(lang ->
                    LanguagesResponse.builder()
                            .langCd(lang.getLangCd())
                            .name(lang.getLangName())
                            .regiDtm(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                            .sts(lang.getStatus().getValue())
                            .build()
                ).collect(Collectors.toList());

        return LanguagesMainResponse.builder()
                .languagesResponses(data)
                .page(languagePage)
                .build();
    }

    @Override
    public void createLanguage(CreateLanguage payload) {

        // Check if language code already exists
        languageRepository.findLangCd(payload.languageCode())
                .ifPresent(langCd -> {
                    throw new BusinessException(StatusCode.LANG_CODE_ALREADY_EXISTS);
                });

        // Check if language name already exists
        languageRepository.findLangName(payload.name())
                .ifPresent(langName -> {
                    throw new BusinessException(StatusCode.LANG_NAME_ALREADY_EXISTS);
                });

        var data = Language.builder()
                .langCd(payload.languageCode())
                .langName(payload.name())
                .status(StatusActive.fromValue(StatusActive.ACTIVATE.getValue()))
                .build();

        languageRepository.save(data);
    }

    @Override
    public Object getLanguageById(Long langCd) {
        return null;
    }

    @Override
    public void enableLanguage(String langCd) {
        Language language = languageRepository.findLangCd(langCd)
                .orElseThrow(() -> new BusinessException(StatusCode.LANG_CODE_NOT_FOUND));

        language.setStatus(StatusActive.fromValue(StatusActive.ACTIVATE.getValue()));

        languageRepository.save(language);
    }

    @Override
    public void disableLanguage(String langCd) throws Throwable {
        Language language = languageRepository.findLangCd(langCd)
                .orElseThrow(() -> new BusinessException(StatusCode.LANG_CODE_NOT_FOUND));

        language.setStatus(StatusActive.fromValue(StatusActive.DEACTIVATE.getValue()));

        languageRepository.save(language);
    }

    @Override
    public void updateLanguages(String langCode, UpdateLanguageRequest payload) throws Throwable {

        Language language = languageRepository.findLangCd(langCode)
                .orElseThrow(() -> new BusinessException(StatusCode.LANG_CODE_NOT_FOUND));

        languageRepository.findLangName(payload.name())
                .ifPresent(langName -> {
                    throw new BusinessException(StatusCode.LANG_NAME_ALREADY_EXISTS);
                });

        language.setLangName(payload.name());

        languageRepository.save(language);
    }

}
