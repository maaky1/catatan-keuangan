package com.github.maaky1.catatan_keuangan.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class TransactionRs {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long trxId;
    private String trxType;
    private String trxCategory;
    private String trxDescription;
    private double trxAmount;
    private LocalDateTime trxDate;
}
