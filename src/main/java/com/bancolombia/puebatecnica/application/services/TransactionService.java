package com.bancolombia.puebatecnica.application.services;

import com.bancolombia.puebatecnica.application.ports.input.TransactionServicePort;
import com.bancolombia.puebatecnica.application.ports.output.AccountPersistencePort;
import com.bancolombia.puebatecnica.application.ports.output.TransactionPersistencePort;
import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.domain.models.Account;
import com.bancolombia.puebatecnica.domain.models.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TransactionService implements TransactionServicePort {

    private final AccountPersistencePort accountPersistencePort;
    private final TransactionPersistencePort transactionPersistencePort;
    @Override
    public String save(String transactionType, String accountNumber, long amount){

        Account account = accountPersistencePort.getAccountById(accountNumber);

        if(account==null) throw new DefaultException("transaction-error","Cuenta no encontrada");
        if(transactionType.equals("DEPOSIT")){
            account.setBalance(account.getBalance() + amount);
        }
        if(transactionType.equals("WITHDRAWAL")){
            if(account.getBalance() < amount) throw new DefaultException("transaction-error","Fondos insuficientes");
            account.setBalance(account.getBalance() - amount);
        }

        accountPersistencePort.save(account);

        Transaction transaction = Transaction.builder()
                .transactionType(transactionType)
                .account(accountNumber)
                .amount(amount)
                .build();
        return ""+transactionPersistencePort.save(transaction).getId();
    }

    @Override
    public List<Transaction> getAllByAccount(String account) {

        return transactionPersistencePort.getTransactions(account);
    }
}
