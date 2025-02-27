package com.bancolombia.puebatecnica.application.ports.input;

public interface AccountServicePort {

    String createAccount(String name, String surname, String id, String idType);
    String getBalance(String accountNumber) throws Exception;
}
