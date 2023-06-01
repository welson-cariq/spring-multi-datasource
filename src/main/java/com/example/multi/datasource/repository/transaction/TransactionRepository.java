package com.example.multi.datasource.repository.transaction;

import com.example.multi.datasource.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
