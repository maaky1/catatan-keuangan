package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import com.github.maaky1.catatan_keuangan.model.CategoryModel;
import com.github.maaky1.catatan_keuangan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
