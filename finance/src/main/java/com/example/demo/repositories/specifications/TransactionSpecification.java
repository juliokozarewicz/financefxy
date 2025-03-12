        package com.example.demo.repositories.specifications;

        import com.example.demo.entities.CategoryEntity;
        import com.example.demo.entities.TransactionEntity;
        import jakarta.persistence.criteria.Predicate;
        import org.springframework.data.jpa.domain.Specification;

        import java.math.BigDecimal;
        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.List;

        public class TransactionSpecification {

            public static Specification<TransactionEntity> filter(

                String transactionName,
                String transactionType,
                String paymentDescription,
                BigDecimal paymentAmount,
                LocalDate initDate,
                LocalDate endDate,
                String payee,
                String documentNumber,
                String category,
                String bankAccount,
                String card,
                String notes,
                String status

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

                    if (transactionType != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("transactionType")),
                                "%" + transactionType.toLowerCase() + "%"
                            )
                        );
                    }

                    if (paymentDescription != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("paymentDescription")),
                                "%" + paymentDescription.toLowerCase() + "%"
                            )
                        );
                    }

                    if (paymentAmount != null) {
                        predicates.add(
                            criteriaBuilder.equal(root.get("paymentAmount"), paymentAmount)
                        );
                    }

                    if (transactionName != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("transactionName")),
                                "%" + transactionName.toLowerCase() + "%"
                            )
                        );
                    }

                    if (initDate != null) {
                        predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                root.get("dueDate"),initDate
                            )
                        );
                    }

                    if (endDate != null) {
                        predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                root.get("dueDate"),
                                endDate
                            )
                        );
                    }

                    if (payee != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("payee")),
                                "%" + payee.toLowerCase() + "%"
                            )
                        );
                    }

                    if (documentNumber != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("documentNumber")),
                                "%" + documentNumber.toLowerCase() + "%"
                            )
                        );
                    }

                    if (category != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("category")),
                                "%" + category.toLowerCase() + "%"
                            )
                        );
                    }

                    if (bankAccount != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("bankAccount")),
                                "%" + bankAccount.toLowerCase() + "%"
                            )
                        );
                    }

                    if (card != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("card")),
                                "%" + card.toLowerCase() + "%"
                            )
                        );
                    }

                    if (notes != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("notes")),
                                "%" + notes.toLowerCase() + "%"
                            )
                        );
                    }

                    if (status != null) {
                        predicates.add(
                            criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("status")),
                                "%" + status.toLowerCase() + "%"
                            )
                        );
                    }

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

                };

            }

        }