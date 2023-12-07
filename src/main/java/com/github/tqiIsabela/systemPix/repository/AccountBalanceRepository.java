package com.github.tqiIsabela.systemPix.repository;
import com.github.tqiIsabela.systemPix.model.AccountBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AccountBalanceRepository extends JpaRepository<AccountBalance, Long> {
    double findBalanceByCpf(Long cpf);

    @Modifying
    @Query("UPDATE AccountBalance SET saldo = :newBalance WHERE cpf = :cpf")
    void updateBalance(Long cpf, double newBalance);

}




