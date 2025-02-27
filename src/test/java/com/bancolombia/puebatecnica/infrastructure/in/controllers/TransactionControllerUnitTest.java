package com.bancolombia.puebatecnica.infrastructure.in.controllers;

import com.bancolombia.puebatecnica.application.ports.input.TransactionServicePort;
import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.domain.models.Transaction;
import com.bancolombia.puebatecnica.domain.models.TransactionType;
import com.bancolombia.puebatecnica.infrastructure.in.dto.TransactionDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class TransactionControllerUnitTest {

    @Mock
    private TransactionServicePort transactionServicePort;

    @InjectMocks
    private TransactionController transactionController;

    TransactionControllerUnitTest(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void save() throws Exception {
        TransactionDTO transactionDTO =  TransactionDTO.builder().transactionType(0).value(1000L).account("001").build();
        when(transactionServicePort.save(Mockito.anyString(), Mockito.anyString(), Mockito.anyLong())).thenReturn("001");
        ResponseEntity<String> response = transactionController.save(transactionDTO);
        Assertions.assertEquals("Transaccion finalizada con exito.", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void saveNoValidTransactionValue() {
        try{
            TransactionDTO transactionDTO =  TransactionDTO.builder().transactionType(0).value(-1000L).account("001").build();
            transactionController.save(transactionDTO);
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("El valor de la transaccion debe ser mayor o igual a 0", e.getMessage());
        }
    }

    @Test
    public void saveNoValidTransactionType() {
        try{
            TransactionDTO transactionDTO =  TransactionDTO.builder().transactionType(2).value(1000L).account("001").build();
            transactionController.save(transactionDTO);
        }catch (Exception e){
            Assertions.assertEquals("Tipo de transaccion incorrecta o inexistente", e.getMessage());
        }
    }

    @Test
    public void saveError() {
        try {
            when(transactionServicePort.save(Mockito.anyString(), Mockito.anyString(), Mockito.anyLong())).thenThrow(new DefaultException("","Error While creating transaction"));
            TransactionDTO transactionDTO = TransactionDTO.builder().transactionType(0).value(1000L).account("001").build();
            transactionController.save(transactionDTO);

            Assertions.fail();

        }catch (Exception e){
            Assertions.assertEquals("Error While creating transaction", e.getMessage());
        }
    }

    @Test
    public void getAllByAccount(){
        List<Transaction> transactionList = List.of(Transaction.builder().id(1).amount(100L).account("001").transactionType(TransactionType.DEPOSIT.name()).build());
        when(transactionServicePort.getAllByAccount(Mockito.anyString())).thenReturn(transactionList);
        ResponseEntity<?> response = transactionController.getAllByAccount("001");
        Assertions.assertEquals(transactionList, response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getAllByAccountUnknownException(){

        try{
            doThrow(new RuntimeException("Unknown Exception"))
                    .when(transactionServicePort)
                    .getAllByAccount(Mockito.anyString());
            transactionController.getAllByAccount("001");
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("Unknown Exception", e.getMessage());
        }
    }

}
