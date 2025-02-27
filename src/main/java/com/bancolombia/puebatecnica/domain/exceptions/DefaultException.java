package com.bancolombia.puebatecnica.domain.exceptions;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DefaultException extends RuntimeException{

    private String code;
    public DefaultException(String code, String message) {
        super(message);
        this.code = code;
    }
}
