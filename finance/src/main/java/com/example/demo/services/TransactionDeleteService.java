package com.example.demo.services;

import com.example.demo.entities.TransactionEntity;
import com.example.demo.middlewares.ErrorHandler;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.utils.StandardResponse;
import com.example.demo.validations.UUIDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionDeleteService {

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

        UUIDValidation idDelete

    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify id
        Optional<TransactionEntity> existingIdTransaction = transactionRepository
            .findById(idDelete.uuid());

        // id not found
        if (existingIdTransaction.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "transaction_not_found", null, locale
                )
            );
        }

        // delete transaction by id
        transactionRepository.deleteById(idDelete.uuid());

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/transaction/delete/" + idDelete.uuid());
        customLinks.put("next", "/finance/transaction/list-all");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                    "transaction_deleted_success", null, locale
                )
            )
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}