package com.bancolombia.puebatecnica.infrastructure.out.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class AccountEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    private String identification;
    @Column(nullable = false)
    private String identificationType;

    @Id
    private String numberAccount;
    private Long balance;
}
