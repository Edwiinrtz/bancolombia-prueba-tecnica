package com.bancolombia.puebatecnica.application.services;

import com.bancolombia.puebatecnica.application.ports.output.AccountPersistencePort;
import com.bancolombia.puebatecnica.application.utils.UtilsAccount;
import com.bancolombia.puebatecnica.domain.models.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

public class AccountServicesTest {

    @Mock
    private UtilsAccount utilsAccount;
    @Mock
    private AccountPersistencePort accountPersistencePort;
    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createAccountSuccessfully(){

        String testAccountNumber = "1000000001";

        when(utilsAccount.generateAccountNumber()).thenReturn(testAccountNumber);
        when(accountPersistencePort.getAccountById(Mockito.anyString()))
                .thenReturn(Mockito.mock(Account.class))
                .thenReturn(null);
        when(accountPersistencePort.save(Mockito.any())).thenReturn(testAccountNumber);

        String accountNumber = accountService.createAccount("", "", "", "");

        Assertions.assertNotNull(accountNumber);
        Assertions.assertEquals("1000000001",accountNumber);
        Assertions.assertFalse(Long.parseLong(accountNumber) < 1000000001L);
    }

    @Test
    public void getBalanceTestAccountFound(){
        try{
            Account testAccount = Account.builder().balance(1000L).build();
            when(accountPersistencePort.getAccountById(Mockito.anyString())).thenReturn(testAccount);
            String balanceTest = accountService.getBalance(Mockito.anyString());

            Assertions.assertEquals("1000", balanceTest);
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    public void getBalanceTestNotAccountFound(){
        try{
            when(accountPersistencePort.getAccountById(Mockito.anyString())).thenReturn(null);
            accountService.getBalance(Mockito.anyString());

            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("noAccountFound", e.getMessage());
        }
    }

}
