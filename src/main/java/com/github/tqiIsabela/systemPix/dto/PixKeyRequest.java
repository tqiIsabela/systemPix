package com.github.tqiIsabela.systemPix.dto;

import lombok.Data;

@Data
public class PixKeyRequest {
    private Long cpf;
    private String email;
    private String phoneNumber;
    private Long accountId;
}
