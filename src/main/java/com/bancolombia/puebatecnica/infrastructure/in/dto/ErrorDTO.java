package com.bancolombia.puebatecnica.infrastructure.in.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDTO {

    private String code;
    private String message;
}
