package com.github.maaky1.catatan_keuangan.controller;

import com.github.maaky1.catatan_keuangan.configuration.PathMapping;
import com.github.maaky1.catatan_keuangan.model.request.DataRangeRq;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.model.request.TransactionRq;
import com.github.maaky1.catatan_keuangan.service.TransactionService;
import com.github.maaky1.catatan_keuangan.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping(PathMapping.BASE_URL_APP + PathMapping.BASE_URL_TRANSACTION)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(PathMapping.URL_CREATE_TRANSACTION)
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRq payload) {
        GenericRq request = CommonUtil.constructPayload(null, null, "create-transaction", payload);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.createTransaction(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @GetMapping(PathMapping.URL_GET_ALL_TRANSACTION)
    public ResponseEntity<?> getAllTransaction() {
        GenericRq request = CommonUtil.constructPayload(null, null, "get-all-transaction", null);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.getAllTransaction(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @GetMapping(PathMapping.URL_GET_BY_ID_TRANSACTION)
    public ResponseEntity<?> getTransactionById(@PathVariable long id) {
        GenericRq request = CommonUtil.constructPayload(null, null, "get-transaction-by-id", id);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.getTransactionById(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @GetMapping(PathMapping.URL_FILTER_BY_TYPE_TRANSACTION)
    public ResponseEntity<?> getTransactionById(@RequestParam String type) {
        GenericRq request = CommonUtil.constructPayload(null, null, "filter-transaction", type);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.getTransactionByType(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @DeleteMapping(PathMapping.URL_DELETE_BY_ID_TRANSACTION)
    public ResponseEntity<?> deleteTransactionById(@PathVariable long id) {
        GenericRq request = CommonUtil.constructPayload(null, null, "delete-transaction-by-id", id);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.deleteTransaction(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @PutMapping(PathMapping.URL_UPDATE_TRANSACTION)
    public ResponseEntity<?> updateTransactionById(@PathVariable long id, @RequestBody TransactionRq payload) {
        payload.setTrxId(id);
        GenericRq request = CommonUtil.constructPayload(null, null, "update-transaction", payload);
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.updateTransaction(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }

    @GetMapping(PathMapping.URL_FILTER_BY_RANGE_TRANSACTION)
    public ResponseEntity<?> getTransactionByDataRange(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                       @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        GenericRq request = CommonUtil.constructPayload(null, null, "get-transaction-with-range", new DataRangeRq().setStartDate(startDate).setEndDate(endDate));
        log.info("[{}][RECEIVE REQUEST][{}][{}]", request.getRequestId(), request.getOperationName(), request.getPayload());
        ResponseEntity response = transactionService.getTransactionByDateRange(request);
        log.info("[{}][RECEIVE COMPLETED][{}]", request.getRequestId(), request.getOperationName());
        return response;
    }
}
