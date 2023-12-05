package com.github.tqiIsabela.systemPix.service.serviceImpl;

import com.github.tqiIsabela.systemPix.dto.PixKeyRequest;
import com.github.tqiIsabela.systemPix.model.PixKey;
import com.github.tqiIsabela.systemPix.repository.PixKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PixKeyServiceImpl implements PixKeyService {

    @Autowired
    private PixKeyRepository pixKeyRepository;

    @Override
    public String createPixKey(PixKeyRequest pixKeyRequest) {
        PixKey pixKey = new PixKey();
        pixKey.setCpf(pixKeyRequest.getCpf());
        pixKey.setEmail(pixKeyRequest.getEmail());
        pixKey.setPhoneNumber(pixKeyRequest.getPhoneNumber());
        pixKey.setAccountId(pixKeyRequest.getAccountId());

        pixKey.setRandomKey(generateRandomKey());

       pixKeyRepository.save(pixKey);
        return pixKey.getRandomKey();
    }

    @Override
    public Optional<PixKey> findByCpf(Long cpf) {
        return  pixKeyRepository.findByCpf(cpf);
    }

    private String generateRandomKey() {
        int length = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomKey = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = new Random().nextInt(characters.length());
            randomKey.append(characters.charAt(index));
        }

        return randomKey.toString();
    }
}
