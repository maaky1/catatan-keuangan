package com.github.maaky1.catatan_keuangan.controller;

import com.github.maaky1.catatan_keuangan.configuration.PathMapping;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.model.request.TransactionRq;
import com.github.maaky1.catatan_keuangan.service.TransactionService;
import com.github.maaky1.catatan_keuangan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(PathMapping.BASE_URL_APP + PathMapping.BASE_URL_TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(PathMapping.URL_CREATE_TRANSACTION)
    public ResponseEntity<?> createTrx(@RequestBody TransactionRq payload) {
        GenericRq request = CommonUtil.constructPayload(null, null, "create-transaction", payload);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.createTransaction(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }
}
