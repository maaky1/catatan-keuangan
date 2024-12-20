package com.github.maaky1.catatan_keuangan.service;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import com.github.maaky1.catatan_keuangan.entity.TransactionEntity;
import com.github.maaky1.catatan_keuangan.exception.CommonException;
import com.github.maaky1.catatan_keuangan.model.request.DataRangeRq;
import com.github.maaky1.catatan_keuangan.model.request.GenericRq;
import com.github.maaky1.catatan_keuangan.model.request.TransactionRq;
import com.github.maaky1.catatan_keuangan.model.response.GenericRs;
import com.github.maaky1.catatan_keuangan.model.response.TransactionRs;
import com.github.maaky1.catatan_keuangan.repository.CategoryRepository;
import com.github.maaky1.catatan_keuangan.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity createTransaction(GenericRq payload) {
        GenericRs<TransactionRs> response = new GenericRs<TransactionRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            TransactionRq body = (TransactionRq) payload.getPayload();
            CategoryEntity categoryEntity = categoryRepository.findByCategoryName(body.getCategoryTrx());
            if (categoryEntity == null) throw new CommonException("01", "Failed", "Category not found");

            TransactionEntity entity = transactionRepository.save(new TransactionEntity()
                    .setTypeTrx(body.getTypeTrx())
                    .setAmountTrx(body.getAmountTrx())
                    .setCategoryTrx(categoryEntity)
                    .setDescriptionTrx(body.getDescriptionTrx())
                    .setDateTrx(body.getDateTrx())
                    .setCreatedOn(LocalDateTime.now()));
            TransactionRs bodyRs = new TransactionRs()
                    .setTrxId(entity.getId())
                    .setTrxType(entity.getTypeTrx())
                    .setTrxCategory(entity.getCategoryTrx().getCategoryName())
                    .setTrxDescription(entity.getDescriptionTrx())
                    .setTrxDate(entity.getDateTrx())
                    .setTrxAmount(entity.getAmountTrx());
            response.setPayload(bodyRs);

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

    public ResponseEntity getAllTransaction(GenericRq payload) {
        GenericRs<List<TransactionRs>> response = new GenericRs<List<TransactionRs>>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            List<TransactionEntity> allTransactions = transactionRepository.findAll();
            if (allTransactions.isEmpty()) throw new CommonException("01", "Failed", "Transaction not found");

            List<TransactionRs> bodyRs = allTransactions
                    .stream()
                    .map(trx -> new TransactionRs()
                            .setTrxId(trx.getId())
                            .setTrxType(trx.getTypeTrx())
                            .setTrxAmount(trx.getAmountTrx())
                            .setTrxCategory(trx.getDescriptionTrx())
                            .setTrxDescription(trx.getDescriptionTrx())
                            .setTrxDate(trx.getDateTrx()))
                    .collect(Collectors.toList());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.OK);
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

    public ResponseEntity getTransactionById(GenericRq payload) {
        GenericRs<TransactionRs> response = new GenericRs<TransactionRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            long id = (long) payload.getPayload();
            TransactionEntity resultById = transactionRepository.findById(id)
                    .orElseThrow(() -> new CommonException("01", "Failed", "Transaction not found with ID: " + id));

            TransactionRs bodyRs = new TransactionRs()
                    .setTrxId(resultById.getId())
                    .setTrxType(resultById.getTypeTrx())
                    .setTrxAmount(resultById.getAmountTrx())
                    .setTrxCategory(resultById.getDescriptionTrx())
                    .setTrxDescription(resultById.getDescriptionTrx())
                    .setTrxDate(resultById.getDateTrx());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.OK);
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

    public ResponseEntity getTransactionByType(GenericRq payload) {
        GenericRs<List<TransactionRs>> response = new GenericRs<List<TransactionRs>>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            String type = (String) payload.getPayload();
            if (type == null || type.trim().isEmpty())
                throw new CommonException("01", "Failed", "Type must be fill");

            List<TransactionRs> bodyRs = transactionRepository.findByTypeTrx(type)
                    .stream()
                    .map(trx -> new TransactionRs()
                            .setTrxId(trx.getId())
                            .setTrxType(trx.getTypeTrx())
                            .setTrxAmount(trx.getAmountTrx())
                            .setTrxCategory(trx.getDescriptionTrx())
                            .setTrxDescription(trx.getDescriptionTrx())
                            .setTrxDate(trx.getDateTrx()))
                    .collect(Collectors.toList());
            if (bodyRs.isEmpty()) throw new CommonException("01", "Failed", "No transactions found for type: " + type);
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.OK);
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

    public ResponseEntity deleteTransaction(GenericRq payload) {
        GenericRs<TransactionRs> response = new GenericRs<TransactionRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            if (payload.getPayload() == null) throw new CommonException("01", "Failed", "Input transaction id");
            long id = (long) payload.getPayload();
            transactionRepository.deleteById(id);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.OK);
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

    public ResponseEntity updateTransaction(GenericRq payload) {
        GenericRs<TransactionRs> response = new GenericRs<TransactionRs>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            TransactionRq body = (TransactionRq) payload.getPayload();
            TransactionEntity resultById = transactionRepository.findById(body.getTrxId())
                    .orElseThrow(() -> new CommonException("01", "Failed", "Transaction not found with ID: " + body.getTypeTrx()));

            CategoryEntity categoryEntity = categoryRepository.findByCategoryName(body.getCategoryTrx());
            if (categoryEntity == null) throw new CommonException("01", "Failed", "Category not found");

            resultById
                    .setTypeTrx(body.getTypeTrx())
                    .setCategoryTrx(categoryEntity)
                    .setAmountTrx(body.getAmountTrx())
                    .setDescriptionTrx(body.getDescriptionTrx());
            TransactionEntity entity = transactionRepository.save(resultById);

            TransactionRs bodyRs = new TransactionRs()
                    .setTrxId(entity.getId())
                    .setTrxType(entity.getTypeTrx())
                    .setTrxCategory(entity.getCategoryTrx().getCategoryName())
                    .setTrxDescription(entity.getDescriptionTrx())
                    .setTrxDate(entity.getDateTrx())
                    .setTrxAmount(entity.getAmountTrx());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.OK);
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

    public ResponseEntity getTransactionByDateRange(GenericRq payload) {
        GenericRs<List<TransactionRs>> response = new GenericRs<List<TransactionRs>>()
                .setCode("00")
                .setStatus("Success")
                .setMessage("Ok");

        try {
            log.info("[{}][START][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            DataRangeRq body = (DataRangeRq) payload.getPayload();
            if (body == null) throw new CommonException("01", "Failed", "Payload can't be null");

            List<TransactionRs> bodyRs = transactionRepository.findByDateTrxBetween(body.getStartDate(), body.getEndDate())
                    .stream()
                    .map(trx -> new TransactionRs()
                            .setTrxId(trx.getId())
                            .setTrxType(trx.getTypeTrx())
                            .setTrxAmount(trx.getAmountTrx())
                            .setTrxCategory(trx.getDescriptionTrx())
                            .setTrxDescription(trx.getDescriptionTrx())
                            .setTrxDate(trx.getDateTrx()))
                    .collect(Collectors.toList());
            if (bodyRs.isEmpty()) throw new CommonException("01", "Failed", "No transactions found for range "+ body.getStartDate() +" -> " + body.getEndDate());
            response.setPayload(bodyRs);

            log.info("[{}][FINISH][{}][{}]", payload.getRequestId(), payload.getOperationName(), payload.getRequestAt());
            return new ResponseEntity(response, HttpStatus.OK);
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
