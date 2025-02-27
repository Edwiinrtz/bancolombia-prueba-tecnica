package com.bancolombia.puebatecnica.application.services;

import com.bancolombia.puebatecnica.application.ports.output.AccountPersistencePort;
import com.bancolombia.puebatecnica.application.ports.output.TransactionPersistencePort;
import com.bancolombia.puebatecnica.domain.models.Account;
import com.bancolombia.puebatecnica.domain.models.Transaction;
import com.bancolombia.puebatecnica.domain.models.TransactionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Mock
    private AccountPersistencePort accountPersistencePort;
    @Mock
    private TransactionPersistencePort transactionPersistencePort;

    @InjectMocks
    private TransactionService transactionService;

    TransactionServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void  saveNotAccountFound(){
        try{
            when(accountPersistencePort.getAccountById(Mockito.anyString())).thenReturn(null);
            transactionService.save("","",0L);
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("Cuenta no encontrada",e.getMessage());
        }
    }

    @Test
    public void  saveDeposit(){
        try{
            Transaction transactionTest = new Transaction(1, "", "",0L);
            Account accountTest = Account.builder().balance(0L).build();

            when(accountPersistencePort.getAccountById(Mockito.anyString())).thenReturn(accountTest);
            when(transactionPersistencePort.save(Mockito.any())).thenReturn(transactionTest);

            String transactionId = transactionService.save("DEPOSIT","",1000L);
            Assertions.assertEquals("1", transactionId);
            Assertions.assertNotNull(transactionId);

        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void  saveWITHDRAWAL(){
        try{
            Transaction transactionTest = new Transaction(1,"", "",1000L);
            Account accountTest = Account.builder().balance(1000L).build();

            when(accountPersistencePort.getAccountById(Mockito.anyString())).thenReturn(accountTest);
            when(transactionPersistencePort.save(Mockito.any())).thenReturn(transactionTest);

            String transactionId = transactionService.save("WITHDRAWAL","",1000L);
            Assertions.assertEquals("1", transactionId);
            Assertions.assertNotNull(transactionId);
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void  saveWITHDRAWALNotEnoughBalance(){
        try{
            Account accountTest = Account.builder().balance(100L).build();

            when(accountPersistencePort.getAccountById(Mockito.anyString())).thenReturn(accountTest);

            transactionService.save("WITHDRAWAL","",1000L);

            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("Fondos insuficientes",e.getMessage());
        }
    }
    @Test
    public  void getAllByAccountEmptyList(){

        when(transactionPersistencePort.getTransactions(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Transaction> emptyList = transactionService.getAllByAccount(Mockito.anyString());
        Assertions.assertEquals(0, emptyList.size());
        Assertions.assertTrue( emptyList.isEmpty());

    }

    @Test
    public  void getAllByAccount(){

        List<Transaction> testList = List.of(
                new Transaction(0, TransactionType.DEPOSIT.name(), "001", 1000L),
                Transaction.builder()
                        .id(1)
                        .transactionType(TransactionType.WITHDRAWAL.name())
                        .account("001")
                        .amount(1000L)
                        .build()
        );
        when(transactionPersistencePort.getTransactions(Mockito.anyString())).thenReturn(testList);
        List<Transaction> transactionList = transactionService.getAllByAccount(Mockito.anyString());
        Assertions.assertEquals(2, transactionList.size());
        Assertions.assertFalse( transactionList.isEmpty());
        Assertions.assertEquals( TransactionType.DEPOSIT.name(),transactionList.get(0).getTransactionType());
        Assertions.assertEquals( TransactionType.WITHDRAWAL.name(),transactionList.get(1).getTransactionType());
        Assertions.assertEquals( transactionList.get(0).getAmount(),transactionList.get(1).getAmount());

    }

}
