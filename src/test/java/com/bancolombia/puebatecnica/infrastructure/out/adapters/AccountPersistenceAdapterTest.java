package com.bancolombia.puebatecnica.infrastructure.out.adapters;

import com.bancolombia.puebatecnica.domain.models.Account;
import com.bancolombia.puebatecnica.infrastructure.out.entity.AccountEntity;
import com.bancolombia.puebatecnica.infrastructure.out.mapper.AccountMapper;
import com.bancolombia.puebatecnica.infrastructure.out.repositories.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class AccountPersistenceAdapterTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    AccountPersistenceAdapter accountPersistenceAdapter;

    AccountPersistenceAdapterTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save(){
        Account accountTest = Account.builder().numberAccount("011").build();
        AccountEntity accountEntityTest = AccountEntity.builder().numberAccount("011").build();

        when(accountRepository.save(Mockito.any())).thenReturn(accountEntityTest);
        when(accountMapper.accountToAccountEntity(Mockito.any())).thenReturn(accountEntityTest);

        String savedId = accountPersistenceAdapter.save(accountTest);

        Assertions.assertEquals(accountTest.getNumberAccount(), savedId);
    }

    @Test
    public void getAccountByIdNoAccountFound(){

        when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        Account accountNotFound = accountPersistenceAdapter.getAccountById("001");
        Assertions.assertNull(accountNotFound);
    }

    @Test
    public void getAccountById(){

        Account account = Account.builder().numberAccount("001").balance(100L).build();
        AccountEntity accountEntity = AccountEntity.builder().numberAccount("001").balance(100L).build();

        when(accountRepository.findById(Mockito.anyString())).thenReturn(Optional.of(accountEntity));
        when(accountMapper.accountEntityToAccount(Mockito.any())).thenReturn(account);

        Account accountFound = accountPersistenceAdapter.getAccountById("001");
        Assertions.assertNotNull(accountFound);
        Assertions.assertEquals("001", accountFound.getNumberAccount());
        Assertions.assertEquals(100L, accountFound.getBalance());
    }

}
