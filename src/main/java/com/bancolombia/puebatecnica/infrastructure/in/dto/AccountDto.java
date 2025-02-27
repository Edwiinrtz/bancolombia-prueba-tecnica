package com.bancolombia.puebatecnica.infrastructure.in.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountDto {
    private String name;
    private String surname;
    private String identification;
    private String identificationType;
}
