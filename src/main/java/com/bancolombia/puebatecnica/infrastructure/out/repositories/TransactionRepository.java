package com.bancolombia.puebatecnica.infrastructure.out.repositories;

import com.bancolombia.puebatecnica.infrastructure.out.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findAllByAccount(String account);
}
