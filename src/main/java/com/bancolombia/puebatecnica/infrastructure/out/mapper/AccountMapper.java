package com.bancolombia.puebatecnica.infrastructure.out.mapper;


import com.bancolombia.puebatecnica.domain.models.Account;
import com.bancolombia.puebatecnica.infrastructure.out.entity.AccountEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEntity accountToAccountEntity(Account account);
    Account accountEntityToAccount(AccountEntity accountEntity);
}
