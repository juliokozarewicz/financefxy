package com.example.demo.controllers;

import com.example.demo.services.TransactionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@Validated
class TransactionListController {

    @Autowired
    private TransactionListService transactionListService;

    @GetMapping("${BASE_URL_FINANCE}/transaction/list-all")
    public ResponseEntity handle(

    ) {

        return transactionListService.execute();

    }

}