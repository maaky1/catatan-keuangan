package com.github.maaky1.catatan_keuangan.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionRq {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long trxId;
    private String typeTrx;
    private String categoryTrx;
    private double amountTrx;
    private String descriptionTrx;
    private LocalDateTime dateTrx;
}
