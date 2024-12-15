package com.github.maaky1.catatan_keuangan.exception;

import lombok.Data;

@Data
public class CommonException extends Exception {

    private final String errorCode; // 01, 02, 99
    private final String errorStatus;  // Kode error (contoh: "NOT_FOUND", "BAD_REQUEST")
    private final String errorMessage;  // Pesan error yang lebih deskriptif

    // Constructor dengan error code dan error message
    public CommonException(String errorCode, String errorStatus, String errorMessage) {
        super(errorMessage);  // Memanggil constructor superclass (Exception)
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }
}
