package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Account;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Transaction;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.repository.AccountRepository;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    
    @Override
    public Transaction registerTransaction(String accountNumber, Transaction transaction) {
        Account account = accountRepository.findById(accountNumber).orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada"));

        Double currentBalance = account.getInitialBalance();
        Double amount = transaction.getAmount();

        if(transaction.getTransactionType().equalsIgnoreCase("Retiro")) {
            if(currentBalance < amount ) {
                throw new IllegalArgumentException("Saldo no disponible");
            }
            currentBalance -= amount;
        } else {
            currentBalance += amount;
        }

        account.setInitialBalance(currentBalance);
        accountRepository.save(account);

        transaction.setAccount(account);
        transaction.setDate(LocalDate.now());
        transaction.setBalance(currentBalance);

        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getByAccount(String accountNumber) {
        return transactionRepository.findByAccount_AccountNumber(accountNumber);
    }

    @Override
    public List<Transaction> getByAccountAndDate(String accountNumber, LocalDate fromDateTime, LocalDate toDateTime) {
        return transactionRepository.findByAccount_AccountNumberAndDateBetween(accountNumber, fromDateTime, toDateTime);
    }
}
