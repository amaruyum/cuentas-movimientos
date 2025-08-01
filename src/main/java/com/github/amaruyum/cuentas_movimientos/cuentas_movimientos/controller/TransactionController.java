package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Transaction;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service.TransactionService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class TransactionController {
    
    private final TransactionService transactionService;

    @PostMapping("/{accountNumber}")
    public ResponseEntity<?> registerTransaction(@PathVariable String accountNumber, @RequestBody Transaction transaction) {
        try {
            Transaction registedTransaction = transactionService.registerTransaction(accountNumber, transaction);
            return ResponseEntity.ok(registedTransaction);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // amount not available 
        }
    }
    
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<Transaction>> listByAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getByAccount(accountNumber));
    }

    @GetMapping("/{accountNumber}/reporte")
    public ResponseEntity<List<Transaction>> listByDate(
        @PathVariable String accountNumber,
        @RequestParam("desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDateTime,
        @RequestParam("hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDateTime) {

            return ResponseEntity.ok(transactionService.getByAccountAndDate(accountNumber, fromDateTime, toDateTime));
        }
}
