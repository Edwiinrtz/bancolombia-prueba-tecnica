package com.bancolombia.puebatecnica.infrastructure.in.config;


import com.bancolombia.puebatecnica.domain.exceptions.DefaultException;
import com.bancolombia.puebatecnica.infrastructure.in.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<ErrorDTO> ExceptionHandler(DefaultException e){
        return  ResponseEntity.badRequest().body(ErrorDTO.builder().code(e.getCode()).message(e.getMessage()).build());
    }

}
