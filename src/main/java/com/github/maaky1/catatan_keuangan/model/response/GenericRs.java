package com.github.maaky1.catatan_keuangan.model.response;

import lombok.Data;

@Data
public class GenericRs<T> {

    private String code;
    private String status;
    private String message;
    private T payload;
}
