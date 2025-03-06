package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @JsonIgnore
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "transaction_name", nullable = false, length = 100)
    private String transactionName;

    @Column(name = "transaction_type", nullable = false, length = 100)
    private String transactionType = "expense";

    @Column(name = "payment_description", nullable = false, length = 255)
    private String paymentDescription;

    @Column(name = "payment_amount", nullable = false, precision = 18, scale = 2)
    private BigDecimal paymentAmount;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @Column(name = "payee", length = 250)
    private String payee;

    @Column(name = "document_number", length = 250)
    private String documentNumber;

    @Column(name = "category", nullable = false, length = 100)
    private String category;

    @Column(name = "bank_account", nullable = false, length = 100)
    private String bankAccount;

    @Column(name = "card", length = 100)
    private String card;

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "pending";
}
