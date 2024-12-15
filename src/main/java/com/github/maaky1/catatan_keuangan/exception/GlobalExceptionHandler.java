package com.github.maaky1.catatan_keuangan.exception;

import com.github.maaky1.catatan_keuangan.model.response.GenericRs;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle exception untuk CommonException (misalnya kategori tidak ditemukan)
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<GenericRs> handleCommonException(CommonException ex) {
        GenericRs response = new GenericRs()
                .setCode(ex.getErrorCode())
                .setStatus(ex.getErrorStatus())
                .setMessage(ex.getErrorMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // Mengirimkan HTTP 400 (Bad Request)
    }
}
