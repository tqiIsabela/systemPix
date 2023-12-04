package com.github.tqiIsabela.systemPix.controller;

import com.github.tqiIsabela.systemPix.dto.PixTransactionRequest;
import com.github.tqiIsabela.systemPix.exception.InsufficientBalanceException;
import com.github.tqiIsabela.systemPix.model.PixTransaction;
import com.github.tqiIsabela.systemPix.service.serviceImpl.PixTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix")
public class PixTransactionController {

    @Autowired
    private PixTransactionService pixTransactionService;

    @PostMapping("/send")
    public ResponseEntity<?> sendPix(@RequestBody PixTransactionRequest pixTransactionRequest) {
        try {
            PixTransaction pixTransaction = pixTransactionService.sendPix(
                    pixTransactionRequest.getSenderCpf(),
                    pixTransactionRequest.getReceiverCpf(),
                    pixTransactionRequest.getAmount(),
                    pixTransactionRequest.getKeyType()
            );

            return ResponseEntity.ok(pixTransaction);
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Saldo insuficiente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar transação.");
        }
    }
}
