package com.example.demo.validations;

import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Date;

public record TransactionUpdateValidation(

    @Size(min = 1, message = "{not_empty}")
    @Size(max = 100, message = "{many_characters}")
    String transactionName,

    @Size(max = 100, message = "{many_characters}")
    String transactionType,

    @Size(max = 255, message = "{many_characters}")
    String paymentDescription,

    BigDecimal paymentAmount,

    Date dueDate,

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