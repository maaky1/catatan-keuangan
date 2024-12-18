package com.github.maaky1.catatan_keuangan.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "transaction", schema = "master")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String typeTrx; // income, expsense
    @Column(nullable = false)
    private double amountTrx;
    @Column(nullable = false)
    private String descriptionTrx;
    @Column(nullable = false)
    private LocalDateTime dateTrx;
    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
}