package com.example.demo.services;

import com.example.demo.entities.TransactionEntity;
import com.example.demo.middlewares.ErrorHandler;
import com.example.demo.repositories.TransactionRepository;
import com.example.demo.utils.StandardResponse;
import com.example.demo.validations.TransactionUpdateValidation;
import com.example.demo.validations.UUIDValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class TransactionUpdateService {

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
        UUIDValidation idUpdate,
        TransactionUpdateValidation validatedBody
    ) {

        // language
        Locale locale = LocaleContextHolder.getLocale();

        // verify id
        Optional<TransactionEntity> existingTransactionId = transactionRepository
            .findById(idUpdate.uuid());

        if (existingTransactionId.isEmpty()) {
            // call custom error
            errorHandler.customErrorThrow(
                404,
                messageSource.getMessage(
                    "transaction_not_found", null, locale
                )
            );
        }

        // verify transaction
        // due date
        LocalDate dueDate = validatedBody.dueDate() != null
            ? validatedBody.dueDate().toLocalDate()
            : existingTransactionId.get().getDueDate();

        Optional<TransactionEntity> existingTransaction = transactionRepository
            .findByTransactionNameAndDueDate(
                validatedBody.transactionName(),
                dueDate
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

        // builder new data
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneOffset.UTC);
        Timestamp nowTimestamp = Timestamp.from(nowUtc.toInstant());

        TransactionEntity updateTransaction = TransactionEntity.builder()
            .id(idUpdate.uuid())
            .updatedAt(nowTimestamp.toLocalDateTime())

            .transactionName(
                (
                    validatedBody.transactionName() != null &&
                        validatedBody.transactionName().trim().isEmpty()
                )
                ? validatedBody.transactionName().trim()
                : existingTransactionId.get().getTransactionName()
            )

            .transactionType(
                (
                    validatedBody.transactionType() != null &&
                        validatedBody.transactionType().trim().isEmpty()
                )
                    ? validatedBody.transactionType().trim()
                    : existingTransactionId.get().getTransactionType()
            )

            .paymentDescription(
                (
                    validatedBody.paymentDescription() != null &&
                        validatedBody.paymentDescription().trim().isEmpty()
                )
                    ? validatedBody.paymentDescription().trim()
                    : existingTransactionId.get().getPaymentDescription()
            )

            .paymentAmount(
                (
                    validatedBody.paymentAmount() != null
                )
                    ? validatedBody.paymentAmount()
                    : existingTransactionId.get().getPaymentAmount()
            )

            .dueDate(dueDate)

            .payee(
                (
                    validatedBody.payee() != null &&
                        validatedBody.payee().trim().isEmpty()
                )
                    ? validatedBody.payee().trim()
                    : existingTransactionId.get().getPayee()
            )

            .documentNumber(
                (
                    validatedBody.documentNumber() != null &&
                        validatedBody.documentNumber().trim().isEmpty()
                )
                    ? validatedBody.documentNumber().trim()
                    : existingTransactionId.get().getDocumentNumber()
            )

            .category(
                (
                    validatedBody.category() != null &&
                        validatedBody.category().trim().isEmpty()
                )
                    ? validatedBody.category().trim()
                    : existingTransactionId.get().getCategory()
            )

            .bankAccount(
                (
                    validatedBody.bankAccount() != null &&
                        validatedBody.bankAccount().trim().isEmpty()
                )
                    ? validatedBody.bankAccount().trim()
                    : existingTransactionId.get().getBankAccount()
            )

            .card(
                (
                    validatedBody.card() != null &&
                        validatedBody.card().trim().isEmpty()
                )
                    ? validatedBody.card().trim()
                    : existingTransactionId.get().getCard()
            )

            .notes(
                (
                    validatedBody.notes() != null &&
                        validatedBody.notes().trim().isEmpty()
                )
                    ? validatedBody.notes().trim()
                    : existingTransactionId.get().getNotes()
            )

            .status(
                (
                    validatedBody.status() != null &&
                        validatedBody.status().trim().isEmpty()
                )
                    ? validatedBody.status().trim()
                    : existingTransactionId.get().getStatus()
            )

        .build();

        transactionRepository.save(updateTransaction);

        // response (json)
        Map<String, String> customLinks = new LinkedHashMap<>();
        customLinks.put("self", "/finance/transaction/update");
        customLinks.put("next", "/finance/transaction/list-all");

        StandardResponse response = new StandardResponse.Builder()
            .statusCode(200)
            .statusMessage("success")
            .message(
                messageSource.getMessage(
                     "transaction_updated_success", null, locale
                ) + dueDate
            )
            .links(customLinks)
            .build();
        return ResponseEntity
            .status(response.getStatusCode())
            .body(response);

    }

}