package com.bancolombia.puebatecnica.infrastructure.out.repositories;


import com.bancolombia.puebatecnica.domain.models.Account;
import com.bancolombia.puebatecnica.infrastructure.out.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, String> { }
