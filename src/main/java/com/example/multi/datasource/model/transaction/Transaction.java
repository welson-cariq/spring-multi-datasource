package com.example.multi.datasource.model.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tb_transaction")
public class Transaction {

    @Id
    private UUID id;
    private BigDecimal amount;
    private UUID userId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
