package com.example.demo.controllers;

import com.example.demo.services.TransactionCreateService;
import com.example.demo.validations.TransactionCreateValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Validated
class TransactionCreateController {

    @Autowired
    private TransactionCreateService transactionCreateService;

    @PostMapping("${BASE_URL_FINANCE}/transaction/create")
    public ResponseEntity handle(

        // validations errors
        @Valid @RequestBody TransactionCreateValidation transactionCreateValidation,
        BindingResult bindingResult

    ) {

        return transactionCreateService.execute(transactionCreateValidation);

    }

}