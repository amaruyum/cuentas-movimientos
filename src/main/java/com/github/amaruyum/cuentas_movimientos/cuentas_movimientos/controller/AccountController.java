package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Account;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service.AccountService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor

public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/{numeroCuenta}")
    public ResponseEntity<Account> getByNumber(@PathVariable String accountNumber) {
        return accountService.getByNumber(accountNumber).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Account account) {
        try {
            Account accountNew = accountService.create(account);
            return ResponseEntity.ok(accountNew);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<?> update(@PathVariable String accountNumber, @RequestBody Account account) {
        try {
            Account updatedAccount = accountService.updateAccount(accountNumber, account);
            return ResponseEntity.ok(updatedAccount);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> delete(@PathVariable String accountNumber) {
        accountService.delete(accountNumber);
        return ResponseEntity.noContent().build();
    }
    
}
