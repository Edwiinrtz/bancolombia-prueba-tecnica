package com.bancolombia.puebatecnica.application.ports.input;

import com.bancolombia.puebatecnica.domain.models.Transaction;

import java.util.List;

public interface TransactionServicePort{
    String save(String transactionType, String account, long amount);
    List<Transaction> getAllByAccount(String account);
}
