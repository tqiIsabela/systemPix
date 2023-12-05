package com.github.tqiIsabela.systemPix.controller;

import com.github.tqiIsabela.systemPix.dto.PixKeyRequest;
import com.github.tqiIsabela.systemPix.service.serviceImpl.PixKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pix-key")
public class PixKeyController {
    @Autowired
    private PixKeyService pixKeyService;

    @PostMapping("/create")
    public ResponseEntity<String> createPixKey(@RequestBody PixKeyRequest pixKeyRequest) {
        try {
            String randomKey = pixKeyService.createPixKey(pixKeyRequest);
            return ResponseEntity.ok(randomKey);
        } catch ( Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar chave pix");
        }
    }
}
