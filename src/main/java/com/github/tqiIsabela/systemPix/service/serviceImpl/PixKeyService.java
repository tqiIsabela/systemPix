package com.github.tqiIsabela.systemPix.service.serviceImpl;

import com.github.tqiIsabela.systemPix.dto.PixKeyRequest;
import com.github.tqiIsabela.systemPix.model.PixKey;

import java.util.Optional;

public interface PixKeyService {
    String createPixKey(PixKeyRequest pixKeyRequest);

    Optional<PixKey> findByCpf(Long cpf);

    void updatePixKey(PixKeyRequest pixKeyRequest);
}
