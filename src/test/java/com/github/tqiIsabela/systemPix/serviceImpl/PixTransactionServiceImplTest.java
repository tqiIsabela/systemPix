package com.github.tqiIsabela.systemPix.serviceImpl;

import com.github.tqiIsabela.systemPix.exception.InsufficientBalanceException;
import com.github.tqiIsabela.systemPix.model.PixTransaction;
import com.github.tqiIsabela.systemPix.model.User;
import com.github.tqiIsabela.systemPix.repository.AccountBalanceRepository;
import com.github.tqiIsabela.systemPix.repository.PixTransactionRepository;
import com.github.tqiIsabela.systemPix.repository.UserRepository;
import com.github.tqiIsabela.systemPix.service.serviceImpl.PixTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PixTransactionServiceImplTest {

    @Mock
    private PixTransactionRepository transactionRepository;

    @Mock
    private AccountBalanceRepository balanceRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PixTransactionServiceImpl pixTransactionService;

    private User sender;
    private User receiver;

    @BeforeEach
    public void setUp() {
        sender = new User();
        receiver = new User();

        sender.setCpf(111L);
        sender.setNome("Sender Name");

        receiver.setCpf(222L);
        receiver.setNome("Receiver Name");

        Mockito.lenient().when(userRepository.findByCpf(111L)).thenReturn(sender);
        Mockito.lenient().when(userRepository.findByCpf(222L)).thenReturn(receiver);
        Mockito.lenient().when(balanceRepository.findBalanceByCpf(anyLong())).thenReturn(1000.0);
        Mockito.lenient().when(transactionRepository.save(any(PixTransaction.class))).thenAnswer(invocation -> {
            PixTransaction savedTransaction = invocation.getArgument(0);
            savedTransaction.setId(1L);
            savedTransaction.setNome("Sender Name");
            return savedTransaction;
        });
    }

    @Test
    void sendPix_SuccessfulTransaction() {
        PixTransaction pixTransaction = pixTransactionService.sendPix(111L, 222L, 200.0, "email");

        assertNotNull(pixTransaction);
        assertEquals("Sender Name", pixTransaction.getNome());
        assertEquals(111L, pixTransaction.getCpf());
        assertEquals(200.0, pixTransaction.getValor());
        assertEquals(LocalDateTime.now().getDayOfYear(), pixTransaction.getDataHoraTransacao().getDayOfYear());
        assertEquals("Banco Exemplo", pixTransaction.getBanco());
        assertEquals(PixTransaction.TipoOperacao.ENVIADO, pixTransaction.getTipoOperacao());
        assertEquals("CHAVE_EXEMPLO", pixTransaction.getChave());

        verify(transactionRepository, times(2)).save(any(PixTransaction.class));
        verify(balanceRepository, times(2)).updateBalance(anyLong(), anyDouble());
    }

    @Test
    void sendPix_InsufficientBalanceException() {
        assertThrows(InsufficientBalanceException.class, () -> pixTransactionService.sendPix(111L, 222L, 1200.0, "email"));

        verify(transactionRepository, never()).save(any(PixTransaction.class));
        verify(balanceRepository, never()).updateBalance(anyLong(), anyDouble());
    }

    @Test
    void getTransactionsByCpf_Successful() {
        List<PixTransaction> transactions = new ArrayList<>();
        transactions.add(new PixTransaction());
        transactions.add(new PixTransaction());

        when(transactionRepository.findByCpf(111L)).thenReturn(transactions);

        List<PixTransaction> result = pixTransactionService.getTransactionsByCpf(111L);

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}




