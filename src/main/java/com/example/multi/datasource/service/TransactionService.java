package com.example.multi.datasource.service;

import com.example.multi.datasource.model.transaction.Transaction;
import com.example.multi.datasource.repository.transaction.TransactionRepository;
import com.example.multi.datasource.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public Transaction create(Transaction transaction) {
        var user = userRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("invalid user"));

        transaction.setId(UUID.randomUUID());
        transaction.setUserId(user.getId());
        return transactionRepository.save(transaction);
    }
}
