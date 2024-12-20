package com.github.maaky1.catatan_keuangan.controller;

import com.github.maaky1.catatan_keuangan.configuration.PathMapping;
import com.github.maaky1.catatan_keuangan.model.request.CategoryRq;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.service.CategoryService;
import com.github.maaky1.catatan_keuangan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(PathMapping.BASE_URL_APP + PathMapping.BASE_URL_CATEGORY)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping(PathMapping.URL_CREATE_CATEGORY)
    public ResponseEntity<?> createCategory(@RequestBody CategoryRq payload) {
        GenericRq request = CommonUtil.constructPayload(null, null, "create-category", payload);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = categoryService.createCategory(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @GetMapping(PathMapping.URL_GET_ALL_CATEGORY)
    public ResponseEntity<?> getAllCategory() {
        GenericRq request = CommonUtil.constructPayload(null, null, "get-all-category", null);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = categoryService.getAllCategory(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @GetMapping(PathMapping.URL_GET_BY_ID_CATEGORY)
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {
        GenericRq request = CommonUtil.constructPayload(null, null, "get-category-by-id", id);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = categoryService.getCategoryById(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @DeleteMapping(PathMapping.URL_DELETE_BY_ID_CATEGORY)
    public ResponseEntity<?> deleteCategoryById(@PathVariable long id) {
        GenericRq request = CommonUtil.constructPayload(null, null, "delete-category-by-id", id);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = categoryService.deleteCategory(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }
}
