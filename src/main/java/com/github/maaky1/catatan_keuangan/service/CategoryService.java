package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import com.github.maaky1.catatan_keuangan.model.request.CategoryRq;
import com.github.maaky1.catatan_keuangan.model.response.CategoryRs;
import com.github.maaky1.catatan_keuangan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryRs createCategory(CategoryRq payload) {
        CategoryEntity entity = categoryRepository.save(new CategoryEntity().setCategoryName(payload.getCategoryName()));
        return new CategoryRs()
                .setCategoryId(entity.getId())
                .setCategoryName(entity.getCategoryName());
    }

    public List<CategoryRs> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryRs()
                        .setCategoryId(category.getId())
                        .setCategoryName(category.getCategoryName()))
                .collect(Collectors.toList());
    }

    public CategoryRs getCategoryById(long id) {
        return categoryRepository.findById(id)
                .map(category -> new CategoryRs()
                        .setCategoryId(category.getId())
                        .setCategoryName(category.getCategoryName()))
                .orElse(null);
    }

    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
