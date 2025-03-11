package com.example.demo.services;

import com.example.demo.entities.TransactionEntity;
import com.example.demo.middlewares.ErrorHandler;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.utils.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class TransactionListService {

    // message source
    @Autowired
    private MessageSource messageSource;

    // error handler
    @Autowired
    private ErrorHandler errorHandler;

    // transaction entity
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity execute() {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // list all categories
        List<TransactionEntity> allTransactions = transactionRepository
            .findAll();

        // response (meta)
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("totalItems", allTransactions.size());

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/transaction/list-all");
        customLinks.put("next", "/finance/transaction/update/{id}");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "get_data_success", null, locale
                )
            )
            .data(allTransactions)
            .meta(metaInfo)
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}