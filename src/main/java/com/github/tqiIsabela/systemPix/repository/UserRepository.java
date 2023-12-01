package com.github.tqiIsabela.systemPix.repository;

import com.github.tqiIsabela.systemPix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCpf(Long cpf);
}

