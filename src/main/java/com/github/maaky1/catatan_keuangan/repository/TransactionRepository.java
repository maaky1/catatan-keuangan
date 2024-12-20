package com.github.maaky1.catatan_keuangan.repository;

import com.github.maaky1.catatan_keuangan.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByTypeTrx(String type);
    List<TransactionEntity> findByDateTrxBetween(LocalDateTime startDate, LocalDateTime endDate);
}
