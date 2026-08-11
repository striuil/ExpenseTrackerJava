package org.striuil.expensetrackerjava.contorller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.striuil.expensetrackerjava.entity.Category;
import org.striuil.expensetrackerjava.repository.CategoryRepository;
import org.striuil.expensetrackerjava.service.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public Category saveCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping
    public List<Category> getCategoryList() {
        return categoryService.fetchCategoryList();
    }

    @GetMapping("/{id}")
    public Optional<Category> getCategoryById(@PathVariable("id") Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@Valid @RequestBody Category category, @PathVariable("id") Long categoryId) {
        return categoryService.updateCategory(category, categoryId);
    }

    @DeleteMapping("/{id}")
    public String deleteCategoryById(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return "Deleted Successfully";
    }
}
