package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    //Search transaction by account
    List<Transaction> findByAccount_AccountNumber(String accountNumber);

    //Search transaction by account and by date
    List<Transaction> findByAccount_AccountNumberAndDateBetween(String accountNumber, LocalDate fromDateTime, LocalDate toDateTime);
}
