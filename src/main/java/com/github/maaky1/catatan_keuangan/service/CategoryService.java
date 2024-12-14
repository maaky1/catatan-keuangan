package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import com.github.maaky1.catatan_keuangan.model.CategoryModel;
import com.github.maaky1.catatan_keuangan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategory(CategoryModel payload) {
        CategoryEntity entity = categoryRepository.save(new CategoryEntity().setCategoryName(payload.getCategoryName()));
        return new CategoryModel()
                .setCategoryId(entity.getId())
                .setCategoryName(entity.getCategoryName());
    }

    public List<CategoryModel> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryModel()
                        .setCategoryId(category.getId())
                        .setCategoryName(category.getCategoryName()))
                .collect(Collectors.toList());
    }

    public CategoryModel getCategoryById(long id) {
        return categoryRepository.findById(id)
                .map(category -> new CategoryModel()
                        .setCategoryId(category.getId())
                        .setCategoryName(category.getCategoryName()))
                .orElse(null);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
