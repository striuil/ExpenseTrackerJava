package org.striuil.expensetrackerjava.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.striuil.expensetrackerjava.entity.Category;
import org.striuil.expensetrackerjava.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> fetchCategoryList() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category categoryToUpdate = categoryRepository.findById(categoryId).orElseThrow(() -> new EntityNotFoundException("No expense found with id: " + categoryId));

        if (category.getName() != null) {
            if ("".equalsIgnoreCase(category.getName())) {
                throw new IllegalArgumentException("Category name cannot be empty");
            }
            categoryToUpdate.setName(category.getName());
        }

        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
