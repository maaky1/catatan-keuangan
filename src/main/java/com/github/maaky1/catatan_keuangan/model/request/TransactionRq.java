package com.github.maaky1.catatan_keuangan.model.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRq {

    private String typeTrx;
    private double amountTrx;
    private String descriptionTrx;
    private LocalDateTime dateTrx;
}
