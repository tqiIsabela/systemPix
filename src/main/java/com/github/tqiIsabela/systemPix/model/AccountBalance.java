package com.github.tqiIsabela.systemPix.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Entity
@Table(name = "account_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long cpf; // A chave primária é o CPF

    @Column(nullable = false)
    private double saldo;

}




