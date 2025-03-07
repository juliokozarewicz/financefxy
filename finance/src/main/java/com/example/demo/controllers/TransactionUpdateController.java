package com.example.demo.controllers;

import com.example.demo.services.TransactionUpdateService;
import com.example.demo.validations.TransactionUpdateValidation;
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
class TransactionUpdateController {

    @Autowired
    private TransactionUpdateService transactionUpdateService;

    @PutMapping("${BASE_URL_FINANCE}/transaction/update/{id}")
    public ResponseEntity handle(

        // validations errors
        @Valid @PathVariable UUIDValidation id,

        @Valid @RequestBody TransactionUpdateValidation transactionUpdateValidation,
        BindingResult bindingResult

    ) {

        return transactionUpdateService.execute(id, transactionUpdateValidation);

    }

}