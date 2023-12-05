package com.github.tqiIsabela.systemPix.repository;

import com.github.tqiIsabela.systemPix.model.PixKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PixKeyRepository extends JpaRepository<PixKey, Long> {
    Optional<PixKey> findByCpf(Long cpf);
}
