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
public class CategoryCreateService {

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
        CategoryCreateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify category
        Optional<CategoryEntity> existingCategory = categoryRepository
            .findByCategoryName(validatedBody.categoryName());

        if (!existingCategory.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                409,
                messageSource.getMessage(
                    "category_created_conflict", null, locale
                )
            );
        }

        // record category
        String generatedUUID = UUID.randomUUID().toString();
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Create a new CategoryEntity using builder
        CategoryEntity newCategory = CategoryEntity.builder()
            .id(generatedUUID)
            .createdAt(nowTimestamp.toLocalDateTime())
            .updatedAt(nowTimestamp.toLocalDateTime())
            .categoryName(validatedBody.categoryName())
            .build();

        categoryRepository.save(newCategory);

        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("idCreated", generatedUUID);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/category/create");
        customLinks.put("next", "/finance/category/list-all");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(201)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "category_created_success", null, locale
                )
            )
            .meta(metaInfo)
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}