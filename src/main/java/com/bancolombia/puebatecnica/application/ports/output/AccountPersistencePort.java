package com.bancolombia.puebatecnica.application.ports.output;

import com.bancolombia.puebatecnica.domain.models.Account;

public interface AccountPersistencePort {
    String save(Account account);
    Account getAccountById(String id);
}
