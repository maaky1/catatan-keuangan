package com.github.maaky1.catatan_keuangan.repository;

import com.github.maaky1.catatan_keuangan.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
