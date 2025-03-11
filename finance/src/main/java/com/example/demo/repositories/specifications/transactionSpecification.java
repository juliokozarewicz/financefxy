package com.example.demo.repositories.specifications;

import com.example.demo.entities.CategoryEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;

public class transactionSpecification {

    public static Specification<CategoryEntity> filterTasks(

        String transactionName

    ) {

        return (
            root,
            query,
            criteriaBuilder
        ) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (transactionName != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("transactionName")),
                        "%" + transactionName.toLowerCase() + "%"
                    )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }

}