package com.github.maaky1.catatan_keuangan.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String categoryName;
    @OneToMany(mappedBy = "categoryTrx", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionEntity> transactions;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}
