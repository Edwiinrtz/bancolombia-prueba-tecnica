package com.bancolombia.puebatecnica.domain.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private int id;
    private String transactionType;
    private String account;
    private long amount;
}
