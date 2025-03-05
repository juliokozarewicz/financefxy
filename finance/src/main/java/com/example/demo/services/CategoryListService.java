package com.example.demo.services;

import com.example.demo.entities.CategoryEntity;
import com.example.demo.middlewares.ErrorHandler;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.utils.StandardResponse;
import com.example.demo.validations.CategoryCreateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class CategoryListService {

    // message source
    @Autowired
    private MessageSource messageSource;

    // error handler
    @Autowired
    private ErrorHandler errorHandler;

    // category entity
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity execute() {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // list all categories
        List<CategoryEntity> allCategories = categoryRepository
            .findAll();

        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("totalItems", allCategories.size());

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/category/list-all");
        customLinks.put("next", "/finance/category/update");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "get_data_success", null, locale
                )
            )
            .data(allCategories)
            .meta(metaInfo)
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}