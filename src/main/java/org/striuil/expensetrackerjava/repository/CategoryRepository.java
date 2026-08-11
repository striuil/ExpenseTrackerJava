package org.striuil.expensetrackerjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.striuil.expensetrackerjava.entity.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findById(Long categoryId);
}
