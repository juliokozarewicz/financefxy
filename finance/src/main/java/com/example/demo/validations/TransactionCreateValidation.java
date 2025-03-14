package com.example.demo.validations;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

public record TransactionCreateValidation(

    @NotEmpty(message = "{not_empty}")
    @Size(min = 1, message = "{not_empty}")
    @Size(max = 100, message = "{many_characters}")
    String transactionName,

    @NotEmpty(message = "{not_empty}")
    @Size(max = 100, message = "{many_characters}")
    String transactionType,

    @NotEmpty(message = "{not_empty}")
    @Size(max = 255, message = "{many_characters}")
    String paymentDescription,

    @NotNull(message = "{not_empty}")
    BigDecimal paymentAmount,

    @NotNull(message = "{not_empty}")
    Date dueDate,

    @Size(max = 250, message = "{many_characters}")
    String payee,

    @Size(max = 250, message = "{many_characters}")
    String documentNumber,

    @NotEmpty(message = "{not_empty}")
    @Size(max = 100, message = "{many_characters}")
    String category,

    @NotEmpty(message = "{not_empty}")
    @Size(max = 100, message = "{many_characters}")
    String bankAccount,

    @Size(max = 100, message = "{many_characters}")
    String card,

    @Size(max = 500, message = "{many_characters}")
    String notes,

    @NotEmpty(message = "{not_empty}")
    @Size(max = 50, message = "{many_characters}")
    String status
) {}