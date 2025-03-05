package com.example.demo.controllers;

import com.example.demo.services.CategoryDeleteService;
import com.example.demo.validations.UUIDValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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