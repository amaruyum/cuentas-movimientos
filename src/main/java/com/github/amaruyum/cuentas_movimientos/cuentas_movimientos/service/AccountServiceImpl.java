package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Account;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.repository.AccountRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    
    private final AccountRepository accountRepository;

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getByNumber(String accountNumber) {
        return accountRepository.findById(accountNumber);
    }

    @Override
    public Account create(Account account) {
        if(accountRepository.existsById(account.getAccountNumber())) {
            throw new IllegalArgumentException("La cuenta ya existe");
        }
        System.out.println("Cuenta a guardar"  + account);
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(String accountNumber, Account account) {
        Account existingAccount = accountRepository.findById(accountNumber)
            .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));
        
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setInitialBalance(account.getInitialBalance());
        existingAccount.setAccountStatus(account.getAccountStatus());

        return accountRepository.save(existingAccount);
    }

    @Override
    public void delete (String accountNumber) {
        accountRepository.deleteById(accountNumber);
    }

}
