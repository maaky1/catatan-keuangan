package com.github.maaky1.catatan_keuangan.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class DataRangeRq {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
