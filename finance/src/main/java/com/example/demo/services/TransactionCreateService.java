package com.example.demo.services;

import com.example.demo.entities.TransactionEntity;
import com.example.demo.middlewares.ErrorHandler;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.utils.StandardResponse;
import com.example.demo.validations.TransactionCreateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.*;

@Service
public class TransactionCreateService {

    // message source
    @Autowired
    private MessageSource messageSource;

    // error handler
    @Autowired
    private ErrorHandler errorHandler;

    // transaction entity
    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity execute(
        TransactionCreateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify transaction
        Optional<TransactionEntity> existingTransaction = transactionRepository
            .findByTransactionNameAndDueDate(
                validatedBody.transactionName(),
                validatedBody.dueDate()
            );

        if (!existingTransaction.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                409,
                messageSource.getMessage(
                    "transaction_created_conflict", null, locale
                )
            );
        }





        // response META
        Map<String, Object> metaInfo = new LinkedHashMap<>();
        metaInfo.put("idCreated", "generatedUUID");

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/transaction/create");
        customLinks.put("next", "/finance/transaction/list-all");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(201)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "transaction_created_success", null, locale
                )
            )
            .meta(metaInfo)
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}