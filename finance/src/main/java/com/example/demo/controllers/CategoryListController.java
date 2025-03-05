package com.example.demo.controllers;

import com.example.demo.services.CategoryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Validated
class CategoryListController {

    @Autowired
    private CategoryListService categorylistService;

    @GetMapping("${BASE_URL_FINANCE}/category/list-all")
    public ResponseEntity handle(

    ) {

        return categorylistService.execute();

    }

}