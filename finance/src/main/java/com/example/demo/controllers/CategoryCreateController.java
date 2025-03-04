package com.example.demo.controllers;

import com.example.demo.validations.CategoryCreateValidation;
import com.example.demo.services.CategoryCreateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Validated
class CategoryCreateController {

    @Autowired
    private CategoryCreateService categoryCreateService;

    @PostMapping("${BASE_URL_FINANCE}/category/create")
    public ResponseEntity handle(

        // validations errors
        @Valid @RequestBody CategoryCreateValidation categoryCreateValidation,
        BindingResult bindingResult

    ) {

        return categoryCreateService.execute(categoryCreateValidation);

    }

}