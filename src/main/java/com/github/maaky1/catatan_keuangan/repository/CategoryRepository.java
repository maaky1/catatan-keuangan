package com.github.maaky1.catatan_keuangan.repository;

import com.github.maaky1.catatan_keuangan.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
