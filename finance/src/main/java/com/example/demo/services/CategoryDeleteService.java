package com.example.demo.services;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.middlewares.ErrorHandler;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.utils.StandardResponse;
import com.example.demo.validations.CategoryUpdateValidation;
import com.example.demo.validations.UUIDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryDeleteService {

    // message source
    @Autowired
    private MessageSource messageSource;

    // error handler
    @Autowired
    private ErrorHandler errorHandler;

    // category entity
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity execute(

        UUIDValidation idUpdate

    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify id
        Optional<CategoryEntity> existingIdCategory = categoryRepository
            .findById(idUpdate.uuid());

        // id not found
        if (existingIdCategory.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "category_not_found", null, locale
                )
            );
        }

        // delete category by id
        categoryRepository.deleteById(idUpdate.uuid());

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/category/delete/" + idUpdate.uuid());
        customLinks.put("next", "/finance/category/list");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "category_deleted_success", null, locale
                )
            )
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}