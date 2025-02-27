package com.bancolombia.puebatecnica.infrastructure.in.controllers;

import com.bancolombia.puebatecnica.application.ports.input.AccountServicePort;
import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.infrastructure.in.dto.AccountDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {


    private final AccountServicePort accountServicePort;

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody AccountDto accountDto){

        try {
            String accountNumber = accountServicePort.createAccount(
                    accountDto.getName(),
                    accountDto.getSurname(),
                    accountDto.getIdentification(),
                    accountDto.getIdentificationType()
            );
            return ResponseEntity.ok("Account created: "+accountNumber);

        }catch (Exception e){
            throw new DefaultException("account-error", e.getMessage());
        }
    }

    @GetMapping("/balance")
    public ResponseEntity<String> getBalance(@RequestParam String accountNumber){
        try {
            return ResponseEntity.ok("Account balance:"+accountServicePort.getBalance(accountNumber));
        }catch (Exception e){
            throw new DefaultException("account-error", e.getMessage());
        }
    }
}
