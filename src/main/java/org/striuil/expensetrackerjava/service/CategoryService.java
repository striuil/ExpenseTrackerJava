package org.striuil.expensetrackerjava.service;

import org.striuil.expensetrackerjava.entity.Category;


import java.util.List;

public interface CategoryService {
    //create
    Category saveCategory(Category category);

    //read
    List<Category> fetchCategoryList();

    //update
    Category updateCategory(Category category, Long categoryId);

    //delete
    void deleteCategoryById(Long categoryId);
}
