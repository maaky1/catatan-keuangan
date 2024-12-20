package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import com.github.maaky1.catatan_keuangan.exception.CommonException;
import com.github.maaky1.catatan_keuangan.model.request.CategoryRq;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.model.response.CategoryRs;
import com.github.maaky1.catatan_keuangan.model.response.GenericRs;
import com.github.maaky1.catatan_keuangan.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity createCategory(GenericRq payload) {
        GenericRs<CategoryRs> response = new GenericRs<CategoryRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            CategoryRq body = (CategoryRq) payload.getPayload();
            if (body.getCategoryName() == null) throw new Exception("Payload is null");
            if (body.getCategoryName().trim().isEmpty())
                throw new CommonException("01", "Failed", "Input category name");
            if (categoryRepository.findByCategoryName(body.getCategoryName()) != null)
                throw new CommonException("01", "Failed", "Category already exists with name: " + body.getCategoryName());

            CategoryEntity entity = categoryRepository.save(new CategoryEntity()
                    .setCategoryName(body.getCategoryName())
                    .setCreatedOn(LocalDateTime.now()));
            CategoryRs bodyRs = new CategoryRs()
                    .setCategoryId(entity.getId())
                    .setCategoryName(entity.getCategoryName());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (CommonException ex) {
            response.setCode(ex.getErrorCode())
                    .setStatus(ex.getErrorStatus())
                    .setMessage(ex.getErrorMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("99")
                    .setStatus("Error")
                    .setMessage(e.getMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAllCategory(GenericRq payload) {
        GenericRs<List<CategoryRs>> response = new GenericRs<List<CategoryRs>>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            List<CategoryEntity> allCategorys = categoryRepository.findAll();
            if (allCategorys.isEmpty()) throw new CommonException("01", "Failed", "Category not found");

            List<CategoryRs> bodyRs = allCategorys.stream()
                    .map(category -> new CategoryRs()
                            .setCategoryId(category.getId())
                            .setCategoryName(category.getCategoryName()))
                    .collect(Collectors.toList());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.FOUND);
        } catch (CommonException ex) {
            response.setCode(ex.getErrorCode())
                    .setStatus(ex.getErrorStatus())
                    .setMessage(ex.getErrorMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("99")
                    .setStatus("Error")
                    .setMessage(e.getMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getCategoryById(GenericRq payload) {
        GenericRs<CategoryRs> response = new GenericRs<CategoryRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            long id = (long) payload.getPayload();
            CategoryEntity resultById = categoryRepository.findById(id)
                    .orElseThrow(() -> new CommonException("01", "Failed", "Category not found with ID: " + id));

            CategoryRs bodyRs = new CategoryRs()
                    .setCategoryId(resultById.getId())
                    .setCategoryName(resultById.getCategoryName());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.FOUND);
        } catch (CommonException ex) {
            response.setCode(ex.getErrorCode())
                    .setStatus(ex.getErrorStatus())
                    .setMessage(ex.getErrorMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("99")
                    .setStatus("Error")
                    .setMessage(e.getMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deleteCategory(GenericRq payload) {
        GenericRs<CategoryRs> response = new GenericRs<CategoryRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            if (payload.getPayload() == null) throw new CommonException("01", "Failed", "Input category name");
            long id = (long) payload.getPayload();
            categoryRepository.deleteById(id);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.FOUND);
        } catch (CommonException ex) {
            response.setCode(ex.getErrorCode())
                    .setStatus(ex.getErrorStatus())
                    .setMessage(ex.getErrorMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("99")
                    .setStatus("Error")
                    .setMessage(e.getMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
