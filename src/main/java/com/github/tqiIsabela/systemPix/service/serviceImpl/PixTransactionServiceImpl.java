package com.github.tqiIsabela.systemPix.service.serviceImpl;

import com.github.tqiIsabela.systemPix.exception.InsufficientBalanceException;
import com.github.tqiIsabela.systemPix.model.PixTransaction;
import com.github.tqiIsabela.systemPix.model.User;
import com.github.tqiIsabela.systemPix.repository.AccountBalanceRepository;
import com.github.tqiIsabela.systemPix.repository.PixTransactionRepository;
import com.github.tqiIsabela.systemPix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PixTransactionServiceImpl implements PixTransactionService {

    @Autowired
    private PixTransactionRepository transactionRepository;

    @Autowired
    private AccountBalanceRepository balanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PixTransaction sendPix(Long senderCpf, Long receiverCpf, double amount, String keyType) {
        User sender = userRepository.findByCpf(senderCpf);
        User receiver = userRepository.findByCpf(receiverCpf);

        validateTransaction(sender, amount);

        double senderBalance = balanceRepository.findBalanceByCpf(senderCpf);
        double newSenderBalance = senderBalance - amount;

        if (newSenderBalance < 0) {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar a transação.");
        }

        PixTransaction senderTransaction = createTransaction(sender, amount, PixTransaction.TipoOperacao.ENVIADO);
        transactionRepository.save(senderTransaction);

        balanceRepository.updateBalance(senderCpf, newSenderBalance);

        PixTransaction receiverTransaction = createTransaction(receiver, amount, PixTransaction.TipoOperacao.RECEBIDO);
        transactionRepository.save(receiverTransaction);

        double receiverBalance = balanceRepository.findBalanceByCpf(receiverCpf);
        double newReceiverBalance = receiverBalance + amount;
        balanceRepository.updateBalance(receiverCpf, newReceiverBalance);

        return senderTransaction;
    }


    @Override
    public List<PixTransaction> getTransactionsByCpf(Long cpf) {
        // método para obter transações por CPF

        return null;
    }

    private PixTransaction createTransaction(User user, double amount, PixTransaction.TipoOperacao operationType) {
        PixTransaction transaction = new PixTransaction();
        transaction.setNome(user.getNome());
        transaction.setCpf(user.getCpf());
        transaction.setValor(amount);
        transaction.setDataHoraTransacao(LocalDateTime.now());
        transaction.setBanco("Banco Exemplo");
        transaction.setTipoOperacao(operationType);

        transaction.setChave("CHAVE_EXEMPLO");

        return transaction;
    }

    private void validateTransaction(User user, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser positivo.");
        }

    }
}



