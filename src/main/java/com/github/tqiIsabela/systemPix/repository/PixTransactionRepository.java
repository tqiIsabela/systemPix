package com.github.tqiIsabela.systemPix.repository;

import com.github.tqiIsabela.systemPix.model.PixTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PixTransactionRepository extends JpaRepository<PixTransaction, Long> {
    List<PixTransaction> findByCpf(Long cpf);

    // Adicione outros métodos conforme necessário
}




