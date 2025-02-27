package com.bancolombia.puebatecnica.infrastructure.out.adapters;

import com.bancolombia.puebatecnica.domain.models.Transaction;
import com.bancolombia.puebatecnica.domain.models.TransactionType;
import com.bancolombia.puebatecnica.infrastructure.out.entity.TransactionEntity;
import com.bancolombia.puebatecnica.infrastructure.out.mapper.TransactionMapper;
import com.bancolombia.puebatecnica.infrastructure.out.repositories.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


public class TransactionPersistenceAdapterTest {


    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @InjectMocks
    TransactionPersistenceAdapter transactionPersistence;


    TransactionPersistenceAdapterTest(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void save() {
        Transaction transactionTest = Transaction.builder().build();
        TransactionEntity transactionEntityTest = TransactionEntity.builder().build();

        when(transactionRepository.save(Mockito.any())).thenReturn(transactionEntityTest);
        when(transactionMapper.transactionToTransactionEntity(Mockito.any())).thenReturn(transactionEntityTest);
        when(transactionMapper.transactionEntityToTransaction(Mockito.any())).thenReturn(transactionTest);

        Transaction nTransaction = transactionPersistence.save(Mockito.mock(Transaction.class));

        Assertions.assertNotNull(nTransaction);
        Assertions.assertEquals(transactionTest, nTransaction);
    }

    @Test
    public void getTransactionsEmptyList() {
        when(transactionRepository.findAllByAccount(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Transaction> emptyTransactionList = transactionPersistence.getTransactions("001");
        Assertions.assertNotNull(emptyTransactionList);
        Assertions.assertTrue(emptyTransactionList.isEmpty());
    }

    @Test
    public void getTransactions() {
        List<Transaction> testList = List.of(
                new Transaction(0, TransactionType.DEPOSIT.name(), "001", 1000L),
                Transaction.builder()
                        .id(1)
                        .transactionType(TransactionType.WITHDRAWAL.name())
                        .account("001")
                        .amount(1000L)
                        .build()
        );

        List<TransactionEntity> transactionEntityList = List.of(
                new TransactionEntity(0, TransactionType.DEPOSIT.name(), "001", 1000L),
                TransactionEntity.builder()
                        .id(1)
                        .transactionType(TransactionType.WITHDRAWAL.name())
                        .account("001")
                        .amount(1000L)
                        .build()
        );

        when(transactionRepository.findAllByAccount(Mockito.anyString())).thenReturn(transactionEntityList);
        when(transactionMapper.transactionEntityListToTransactionList(Mockito.anyList())).thenReturn(testList);
        List<Transaction> transactionList = transactionPersistence.getTransactions("001");


        Assertions.assertNotNull(transactionList);
        Assertions.assertFalse(transactionList.isEmpty());
        Assertions.assertEquals(2, transactionList.size());
        Assertions.assertEquals( TransactionType.DEPOSIT.name(),transactionList.get(0).getTransactionType());
        Assertions.assertEquals( TransactionType.WITHDRAWAL.name(),transactionList.get(1).getTransactionType());
        Assertions.assertEquals( transactionList.get(0).getAmount(),transactionList.get(1).getAmount());
    }
}
