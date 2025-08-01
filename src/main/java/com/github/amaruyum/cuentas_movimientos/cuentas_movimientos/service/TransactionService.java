package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service;

import java.time.LocalDate;
import java.util.List;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Transaction;

public interface TransactionService {
    
    Transaction registerTransaction (String accountNumber, Transaction transaction);
    List<Transaction> getByAccount(String accountNumber);
    List<Transaction> getByAccountAndDate(String accountNumber, LocalDate fromDateTime, LocalDate toDateTime);
}
