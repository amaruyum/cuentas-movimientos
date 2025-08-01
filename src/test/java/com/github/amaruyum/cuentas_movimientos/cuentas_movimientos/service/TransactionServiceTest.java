package com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Account;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.entity.Transaction;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.repository.AccountRepository;
import com.github.amaruyum.cuentas_movimientos.cuentas_movimientos.repository.TransactionRepository;


public class TransactionServiceTest {
    
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test 
    void testRegisterTransaction_SuccessfulDeposit() {
        Account account = new Account("123456", "Ahorro", 5000.0, true, "1234567890");
        Transaction transaction = new Transaction(null, null, "Deposito", 1450.0, null,null);

        when(accountRepository.findById("123456")).thenReturn(java.util.Optional.of(account));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        Transaction resultTransaction = transactionService.registerTransaction("123456", transaction);
        
        assertEquals(6450.0, resultTransaction.getAmount());
        verify(accountRepository).save(account);
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void testRegisterTransaccion_insufficientBalanceWithdrawal() {
        Account account = new Account("123456", "Ahorro", 10.0, true, "1234567890" );
        Transaction transaction = new Transaction(null, null, "Retiro", 233.0, null, null);

        when(accountRepository.findById("123456")).thenReturn(java.util.Optional.of(account));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,() -> {
            transactionService.registerTransaction("123456", transaction);
        });

        assertEquals("Saldo no disponible", exception.getMessage());
    }
}
