package com.bancolombia.puebatecnica.infrastructure.in.dto;

import lombok.*;


@Getter
@Setter
@Builder
public class TransactionDTO {

    private int transactionType;
    private String account;
    private long value;
}
