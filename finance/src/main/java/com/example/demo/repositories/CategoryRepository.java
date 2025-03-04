package com.example.demo.repositories;

import com.example.demo.entities.CategoryEntity;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    // get all categories
    List<CategoryEntity> findAll();

    // find category by name
    Optional<CategoryEntity> findByCategoryName(String categoryName);

    // find category by id
    Optional<CategoryEntity> findById(String id);

    // delete category by id
    @Transactional
    void deleteById(String id);

}