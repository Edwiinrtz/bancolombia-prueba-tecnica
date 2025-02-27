package com.bancolombia.puebatecnica.application.ports.output;

import com.bancolombia.puebatecnica.domain.models.Transaction;
import java.util.List;

public interface TransactionPersistencePort {
    Transaction save(Transaction transaction);
    List<Transaction> getTransactions(String account);
}
