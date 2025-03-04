package com.example.demo.repositories.specifications;

import com.example.demo.entities.CategoryEntity;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class categorySpecification {

    public static Specification<CategoryEntity> filterTasks(

        String categoryName

    ) {

        return (
            root,
            query,
            criteriaBuilder
        ) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (categoryName != null) {
                predicates.add(
                    criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("categoryName")),
                        "%" + categoryName.toLowerCase() + "%"
                    )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };

    }
}