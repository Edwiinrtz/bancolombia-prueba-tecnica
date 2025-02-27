package com.bancolombia.puebatecnica.domain.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void createObjectAllArgumentsConstructor(){
        Account account = new Account("nameTest", "surnameTest", "identificationTest", "identificationTypeTest", "001", 0L);

        Assertions.assertEquals("nameTest", account.getName());
        Assertions.assertEquals("surnameTest", account.getSurname());
        Assertions.assertEquals("identificationTest", account.getIdentification());
        Assertions.assertEquals("identificationTypeTest", account.getIdentificationType());
        Assertions.assertEquals("001", account.getNumberAccount());
        Assertions.assertEquals(0L, account.getBalance());
    }

    @Test
    public void createObjectNoneArgumentsConstructor(){
        Account account = new Account();

        Assertions.assertNull(account.getName());
        Assertions.assertNull(account.getSurname());
        Assertions.assertNull(account.getIdentificationType());
        Assertions.assertNull(account.getIdentification());
        Assertions.assertNull(account.getNumberAccount());
        Assertions.assertNull(account.getBalance());
    }

    @Test
    public  void  createObjetBuilder(){
        Account account = Account.builder()
                .numberAccount("0000000001")
                .balance(1000L)
                .build();
        Assertions.assertEquals("0000000001", account.getNumberAccount());
        Assertions.assertEquals(1000L, account.getBalance());
        Assertions.assertNull( account.getName());
    }
}
