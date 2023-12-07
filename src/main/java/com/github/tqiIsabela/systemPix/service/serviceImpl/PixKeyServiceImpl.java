package com.github.tqiIsabela.systemPix.service.serviceImpl;

import com.github.tqiIsabela.systemPix.dto.PixKeyRequest;
import com.github.tqiIsabela.systemPix.exception.PixKeyNotFoundException;
import com.github.tqiIsabela.systemPix.model.PixKey;
import com.github.tqiIsabela.systemPix.repository.PixKeyRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        boolean useLetters = true;
        boolean useNumbers = false;

        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    @Override
    public void updatePixKey(PixKeyRequest pixKeyRequest) {
        Optional<PixKey> existingPixKey = pixKeyRepository.findById(pixKeyRequest.getCpf());

        if (existingPixKey.isPresent()) {
            PixKey pixKey = existingPixKey.get();

            pixKey.setEmail(pixKeyRequest.getEmail());
            pixKey.setPhoneNumber(pixKeyRequest.getPhoneNumber());
            pixKey.setAccountId(pixKeyRequest.getAccountId());

            pixKeyRepository.save(pixKey);
        } else {
            throw  new PixKeyNotFoundException("Chave PIX não encontrada");
        }
    }

    @Override
    public void deletePixKey(Long cpf) {
        Optional<PixKey> existingPixKey = pixKeyRepository.findById(cpf);

        if (existingPixKey.isPresent()) {
            pixKeyRepository.deleteById(cpf);
        } else {
            throw new PixKeyNotFoundException("Chave pix não encontrada");
        }
    }
}
