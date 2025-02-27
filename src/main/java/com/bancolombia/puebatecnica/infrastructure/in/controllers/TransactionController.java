package com.bancolombia.puebatecnica.infrastructure.in.controllers;

import com.bancolombia.puebatecnica.application.ports.input.TransactionServicePort;
import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.domain.models.TransactionType;
import com.bancolombia.puebatecnica.infrastructure.in.dto.TransactionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {


    private final TransactionServicePort transactionServicePort;
    @PostMapping("/new")
    public ResponseEntity<String> save(@RequestBody TransactionDTO transactionDTO){

        if(transactionDTO.getValue() < 0) throw new DefaultException("transaction-error","El valor de la transaccion debe ser mayor o igual a 0");
        if(transactionDTO.getTransactionType()!=0 && transactionDTO.getTransactionType()!=1 ){
            throw  new DefaultException("transaction-error","Tipo de transaccion incorrecta o inexistente");
        }
        String type = transactionDTO.getTransactionType()==0 ? TransactionType.DEPOSIT.name(): TransactionType.WITHDRAWAL.name();

        transactionServicePort.save(type, transactionDTO.getAccount(), transactionDTO.getValue());
        return ResponseEntity.ok("Transaccion finalizada con exito.");

    }

    @GetMapping
    public ResponseEntity<?> getAllByAccount(@RequestParam String account){

        try{
            return ResponseEntity.ok(transactionServicePort.getAllByAccount(account));
        }catch (Exception e){
            throw new DefaultException("transaction-error",e.getMessage());
        }
    }
}
