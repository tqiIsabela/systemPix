package com.github.tqiIsabela.systemPix.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixTransactionRequest {
    private Long senderCpf;
    private Long receiverCpf;
    private double amount;
    private String keyType;

}
