package com.example.demo.controllers;

import com.example.demo.validations.FinanceValidation;
import com.example.demo.services.FinanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Validated
class FinanceController {

    @Autowired
    private FinanceService helloWorldService;

    @GetMapping("${BASE_URL_FINANCE}/category/create")
    public ResponseEntity handle(

        // validations errors
        @Valid FinanceValidation helloWorldValidation,
        BindingResult bindingResult

    ) {

        // message
        String message = helloWorldValidation.message() != null ?
            helloWorldValidation.message() : "Hello World!";

        return helloWorldService.execute(message);

    }

}