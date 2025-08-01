package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service;

import java.util.List;
import java.util.Optional;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Account;

public interface AccountService {
    
    List<Account> getAll();
    Optional<Account> getByNumber(String accountNumber);
    Account create(Account account);
    Account updateAccount(String accountNumber, Account account);
    void delete(String accountNumber);
    
}
