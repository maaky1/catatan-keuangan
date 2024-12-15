package com.github.maaky1.catatan_keuangan.controller;

import com.github.maaky1.catatan_keuangan.configuration.PathMapping;
import com.github.maaky1.catatan_keuangan.model.request.CategoryRq;
import com.github.maaky1.catatan_keuangan.model.response.CategoryRs;
import com.github.maaky1.catatan_keuangan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathMapping.BASE_URL_APP + PathMapping.BASE_URL_CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(PathMapping.URL_CREATE_CATEGORY)
    public ResponseEntity<?> createCategory(@RequestBody CategoryRq payload) {
        CategoryRs response = categoryService.createCategory(payload);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(PathMapping.URL_GET_ALL_CATEGORY)
    public ResponseEntity<?> getAllCategory() {
        List<CategoryRs> response = categoryService.getAllCategory();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(PathMapping.URL_GET_BY_ID_CATEGORY)
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {
        CategoryRs response = categoryService.getCategoryById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(PathMapping.URL_DELETE_BY_ID_CATEGORY)
    public ResponseEntity<?> deleteCategoryById(@PathVariable long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
