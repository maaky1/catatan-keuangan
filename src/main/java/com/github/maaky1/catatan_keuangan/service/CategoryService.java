package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import com.github.maaky1.catatan_keuangan.exception.CommonException;
import com.github.maaky1.catatan_keuangan.model.request.CategoryRq;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.model.response.CategoryRs;
import com.github.maaky1.catatan_keuangan.model.response.GenericRs;
import com.github.maaky1.catatan_keuangan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    private final GenericRs<CategoryRs> response = new GenericRs<CategoryRs>()
            .setCode("00")
            .setStatus("Success")
            .setMessage("Ok");

    public ResponseEntity createCategory(GenericRq payload) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", payload.getRequestId());
        headers.add("requestAt", payload.getRequestAt().toString().replace("T", " "));

        CategoryRq body = (CategoryRq) payload.getPayload();
        CategoryEntity entity = categoryRepository.save(new CategoryEntity().setCategoryName(body.getCategoryName()));
        CategoryRs bodyRs = new CategoryRs()
                .setCategoryId(entity.getId())
                .setCategoryName(entity.getCategoryName());
        response.setPayload(bodyRs);

        return new ResponseEntity(response, headers, HttpStatus.CREATED);
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
