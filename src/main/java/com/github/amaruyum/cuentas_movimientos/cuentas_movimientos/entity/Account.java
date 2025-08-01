package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    private String accountNumber;

    @Column(nullable = false)
    private String accountType;

    @Column(nullable = false)
    private Double initialBalance;
    
    private Boolean accountStatus;
    
    //Simula el vinculo con el cliente del otro microservicio
    @Column(nullable = false)
    private String clientIdentification;
    
}
