package com.example.demo.validations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public record TransactionListValidation (

    @Size(max = 100, message = "{many_characters}")
    String transactionName,
    
    @Size(max = 100, message = "{many_characters}")
    String transactionType,

    @Size(max = 255, message = "{many_characters}")
    String paymentDescription,

    BigDecimal paymentAmount,

    LocalDate initDate,

    LocalDate endDate,

    @Size(max = 250, message = "{many_characters}")
    String payee,

    @Size(max = 250, message = "{many_characters}")
    String documentNumber,

    @Size(max = 100, message = "{many_characters}")
    String category,

    @Size(max = 100, message = "{many_characters}")
    String bankAccount,

    @Size(max = 100, message = "{many_characters}")
    String card,

    @Size(max = 500, message = "{many_characters}")
    String notes,

    @Size(max = 50, message = "{many_characters}")
    String status
) {}