package com.github.maaky1.catatan_keuangan.util;

import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CommonUtil {

    public synchronized static GenericRq constructPayload(String requestId,
                                                          LocalDateTime requestAt,
                                                          String operationName,
                                                          Object payload
                                                          ) {

        return GenericRq.newBuilder()
                .requestId(requestId != null ? requestId : UUID.randomUUID().toString())
                .requestAt(requestAt != null ? requestAt : LocalDateTime.now())
                .operationName(operationName)
                .payload(payload)
                .build();
    }
}
