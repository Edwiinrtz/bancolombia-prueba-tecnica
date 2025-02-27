package com.bancolombia.puebatecnica.domain.models;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private String name;
    private String surname;
    private String identification;
    private String identificationType;
    private String numberAccount;
    private Long balance;
}
