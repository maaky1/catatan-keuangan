package com.github.maaky1.catatan_keuangan.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class GenericRq<T> {

    private String requestId;
    private LocalDateTime requestAt;
    private String operationName;
    private T payload;

    @Builder(builderMethodName = "newBuilder")
    public GenericRq(String requestId, String operationName, LocalDateTime requestAt , T payload) {
        this.requestId = requestId;
        this.operationName = operationName;
        this.requestAt = requestAt;
        this.payload = payload;
    }
}
