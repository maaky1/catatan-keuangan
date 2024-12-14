package com.github.maaky1.catatan_keuangan.controller;

import com.github.maaky1.catatan_keuangan.configuration.PathMapping;
import com.github.maaky1.catatan_keuangan.model.CategoryModel;
import com.github.maaky1.catatan_keuangan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathMapping.BASE_URL_APP + PathMapping.BASE_URL_CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(PathMapping.URL_CREATE_CATEGORY)
    public ResponseEntity<?> createCategory(@RequestBody CategoryModel payload) {
        CategoryModel category = categoryService.createCategory(payload);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
