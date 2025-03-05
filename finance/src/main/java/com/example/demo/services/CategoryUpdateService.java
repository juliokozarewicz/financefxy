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
import java.util.*;

@Service
public class CategoryUpdateService {

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

        UUIDValidation idUpdate,
        CategoryUpdateValidation validatedBody

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
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        // Update a new CategoryEntity using builder
        CategoryEntity newCategory = CategoryEntity.builder()
            .id(idUpdate.uuid())
            .updatedAt(nowTimestamp.toLocalDateTime())
            .categoryName(validatedBody.categoryName())
            .build();

        categoryRepository.save(newCategory);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/category/create");
        customLinks.put("next", "/finance/category/list-all");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "category_updated_success", null, locale
                )
            )
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}