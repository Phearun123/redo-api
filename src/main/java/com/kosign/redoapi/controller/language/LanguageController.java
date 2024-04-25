package com.kosign.redoapi.controller.language;

import com.kosign.redoapi.controller.RedoRestController;
import com.kosign.redoapi.payload.MultiSortBuilder;
import com.kosign.redoapi.payload.languages.CreateLanguage;
import com.kosign.redoapi.payload.languages.LanguagesCriteria;
import com.kosign.redoapi.payload.languages.UpdateLanguageRequest;
import com.kosign.redoapi.service.language.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ca/v1/settings/languages")
@RequiredArgsConstructor
public class LanguageController extends RedoRestController {

    private final LanguageService languageService;

    @GetMapping("")
    public ResponseEntity<?> getLanguages(
            @RequestParam(value = "search_value", required = false) String searchValue,
            @RequestParam(value = "page_size", defaultValue = "10", required = false) Integer pageSize,
            @RequestParam(value = "page_number", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "langCd:desc") String sortColumns
    ) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));

        var criteria = LanguagesCriteria.builder()
                .searchValue(searchValue)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();

        return ok(languageService.getLanguage(criteria, pageable));
    }

    @PostMapping("")
    public Object createLanguages(@RequestBody @Valid CreateLanguage payload) {
        languageService.createLanguage(payload);
        return ok();
    }

    @PutMapping("/{lang_code}")
    public Object updateLanguages(@PathVariable String lang_code, @RequestBody @Valid UpdateLanguageRequest payload) throws Throwable{
        languageService.updateLanguages(lang_code, payload);
        return ok();
    }

    @PatchMapping("/{lang_code}/enable")
    public Object enableLanguages(@PathVariable String lang_code) throws Throwable{
        languageService.enableLanguage(lang_code);
        return ok();
    }

    @PatchMapping("/{lang_code}/disable")
    public Object disableLanguages(@PathVariable String lang_code) throws Throwable {
        languageService.disableLanguage(lang_code);
        return ok();
    }
}
