package com.bancolombia.puebatecnica.infrastructure.in.controllers;

import com.bancolombia.puebatecnica.application.ports.input.AccountServicePort;
import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.infrastructure.in.dto.AccountDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class AccountControllerUnitTest {

    @Mock
    private AccountServicePort accountServicePort;

    @InjectMocks
    private AccountController accountController;

    AccountControllerUnitTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAccount(){

        when(accountServicePort.createAccount(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn("001");

        AccountDto accountDto = AccountDto.builder().name("").surname("").identification("").identificationType("").build();
        ResponseEntity<String> response = accountController.createAccount(accountDto);
        Assertions.assertEquals("Account created: 001",response.getBody());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void createAccountError(){

        try{
            doThrow(new DefaultException("account-error","Error creating account"))
                    .when(accountServicePort)
                    .createAccount(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString());
            AccountDto accountDto = AccountDto.builder().name("").surname("").identification("").identificationType("").build();
            accountController.createAccount(accountDto);
            Assertions.fail();
        }catch(Exception e){
            Assertions.assertEquals("Error creating account",e.getMessage());
        }
    }

    @Test
    public void getBalanceTest() throws Exception {
        when(accountServicePort.getBalance(Mockito.anyString())).thenReturn("1000");
        ResponseEntity<String> response = accountController.getBalance("");
        Assertions.assertEquals("Account balance:1000", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getBalanceTestError() throws Exception {
        try{
            when(accountServicePort.getBalance(Mockito.anyString())).thenThrow(new DefaultException("","Error from test balance"));
            accountController.getBalance("");
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("Error from test balance", e.getMessage());
        }
    }
}
