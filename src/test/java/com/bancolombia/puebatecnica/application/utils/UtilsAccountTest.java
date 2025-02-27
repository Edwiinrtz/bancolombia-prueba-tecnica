package com.bancolombia.puebatecnica.application.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilsAccountTest {


    private UtilsAccount utilsAccount;

    @BeforeEach
    void setUp() {
        utilsAccount = new UtilsAccount();
    }

    @Test
    public void generateAccountNumber(){

        String numberAccount = utilsAccount.generateAccountNumber();

        Assertions.assertNotNull(numberAccount);
        Assertions.assertEquals(10, numberAccount.length());
        Assertions.assertFalse(Long.parseLong(numberAccount) < 1_000_000_000L);
    }
}
