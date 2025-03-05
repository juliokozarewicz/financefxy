package com.example.demo.controllers;

import com.example.demo.services.CategoryUpdateService;
import com.example.demo.validations.CategoryUpdateValidation;
import com.example.demo.validations.UUIDValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Validated
class CategoryUpdateController {

    @Autowired
    private CategoryUpdateService categoryUpdateService;

    @PutMapping("${BASE_URL_FINANCE}/category/update/{id}")
    public ResponseEntity handle(

        // validations errors
        @Valid @PathVariable UUIDValidation id,

        @Valid @RequestBody CategoryUpdateValidation categoryUpdateValidation,
        BindingResult bindingResult

    ) {

        return categoryUpdateService.execute(id, categoryUpdateValidation);

    }

}