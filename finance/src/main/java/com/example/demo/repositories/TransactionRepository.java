package com.example.demo.repositories;

import com.example.demo.entities.TransactionEntity;
import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends

    JpaRepository<TransactionEntity, UUID>,
    JpaSpecificationExecutor<TransactionEntity>

{

    // get all
    List<TransactionEntity> findAll();

    // find name and due date
    Optional<TransactionEntity> findByTransactionNameAndDueDate(
        String transactionName,
        LocalDate DueDate
    );

    // find by id
    Optional<TransactionEntity> findById(String id);

    // delete by id
    @Transactional
    void deleteById(String id);

}