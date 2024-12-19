package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.TransactionEntity;
import com.github.maaky1.catatan_keuangan.exception.CommonException;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.model.request.TransactionRq;
import com.github.maaky1.catatan_keuangan.model.response.CategoryRs;
import com.github.maaky1.catatan_keuangan.model.response.GenericRs;
import com.github.maaky1.catatan_keuangan.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseEntity createTransaction(GenericRq payload) {
        GenericRs<CategoryRs> response = new GenericRs<CategoryRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            TransactionRq body = (TransactionRq) payload.getPayload();
            if (body.getTypeTrx() == null) throw new CommonException("01", "Failed", "Input transaction type");

            TransactionEntity entity = transactionRepository.save(new TransactionEntity()
                    .setTypeTrx(body.getTypeTrx())
                    .setAmountTrx(body.getAmountTrx())
                    .setDescriptionTrx(body.getDescriptionTrx())
                    .setDateTrx(body.getDateTrx())
                    .setCreatedOn(LocalDateTime.now()));

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.CREATED);
        } catch (CommonException ex) {
            response.setCode(ex.getErrorCode())
                    .setStatus(ex.getErrorStatus())
                    .setMessage(ex.getErrorMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.setCode("99")
                    .setStatus("Error")
                    .setMessage(e.getMessage());

            log.error("[{}][ERROR][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
