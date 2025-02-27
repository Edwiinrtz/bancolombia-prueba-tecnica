package com.bancolombia.puebatecnica.infrastructure.out.adapters;

import com.bancolombia.puebatecnica.application.ports.output.TransactionPersistencePort;
import com.bancolombia.puebatecnica.domain.models.Transaction;
import com.bancolombia.puebatecnica.infrastructure.out.mapper.TransactionMapper;
import com.bancolombia.puebatecnica.infrastructure.out.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionPersistenceAdapter implements TransactionPersistencePort {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    @Override
    public Transaction save(Transaction transaction) {
        return transactionMapper.transactionEntityToTransaction(
                transactionRepository.save(
                        transactionMapper.transactionToTransactionEntity(transaction)
                ));
    }

    @Override
    public List<Transaction> getTransactions(String account) {
        return transactionMapper.transactionEntityListToTransactionList(
                transactionRepository.findAllByAccount(account));
    }
}
