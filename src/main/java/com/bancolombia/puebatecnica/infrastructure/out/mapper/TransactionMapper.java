package com.bancolombia.puebatecnica.infrastructure.out.mapper;

import com.bancolombia.puebatecnica.domain.models.Transaction;
import com.bancolombia.puebatecnica.infrastructure.out.entity.TransactionEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionEntity transactionToTransactionEntity(Transaction transaction);
    Transaction transactionEntityToTransaction(TransactionEntity transactionEntity);

    List<Transaction> transactionEntityListToTransactionList(List<TransactionEntity> transactionEntityList);
}
