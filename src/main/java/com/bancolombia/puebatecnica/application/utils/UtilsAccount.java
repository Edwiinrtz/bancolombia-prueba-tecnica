package com.bancolombia.puebatecnica.application.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UtilsAccount {

    private final Random random;

    UtilsAccount(){
        random = new Random();
    }
    public String generateAccountNumber(){
        return ""+ (1_000_000_000L + random.nextLong(9_000_000_000L));
    }
}
