package com.bancolombia.puebatecnica.domain.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTypeTest {

    @Test
    public void creatingEnumsStrings(){
        String depositName = TransactionType.DEPOSIT.name();
        String WithdrawalName = TransactionType.WITHDRAWAL.name();

        Assertions.assertEquals("DEPOSIT", depositName);
        Assertions.assertEquals("WITHDRAWAL", WithdrawalName);
    }
}
