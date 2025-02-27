package com.bancolombia.puebatecnica.infrastructure.out.adapters;

import com.bancolombia.puebatecnica.application.ports.output.AccountPersistencePort;
import com.bancolombia.puebatecnica.domain.models.Account;
import com.bancolombia.puebatecnica.infrastructure.out.entity.AccountEntity;
import com.bancolombia.puebatecnica.infrastructure.out.mapper.AccountMapper;
import com.bancolombia.puebatecnica.infrastructure.out.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountPersistencePort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Override
    public String save(Account account) {
        return accountRepository.save(accountMapper.accountToAccountEntity(account)).getNumberAccount();
    }

    @Override
    public Account getAccountById(String id) {
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        return accountMapper.accountEntityToAccount(accountEntity.orElse(null));
    }
}
