package com.example.demo.controllers;

import com.example.demo.services.CategoryDeleteService;
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
class CategoryDeleteController {

    @Autowired
    private CategoryDeleteService categoryDeleteService;

    @DeleteMapping("${BASE_URL_FINANCE}/category/delete/{id}")
    public ResponseEntity handle(

        // validations errors
        @Valid @PathVariable UUIDValidation id

    ) {

        return categoryDeleteService.execute(id);

    }

}