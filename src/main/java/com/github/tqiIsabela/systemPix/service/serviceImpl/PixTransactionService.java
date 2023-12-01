package com.github.tqiIsabela.systemPix.service.serviceImpl;

import com.github.tqiIsabela.systemPix.model.PixTransaction;

import java.util.List;

public interface PixTransactionService {
    PixTransaction sendPix(Long senderCpf, Long receiverCpf, double amount, String keyType);
    List<PixTransaction> getTransactionsByCpf(Long cpf);
}




