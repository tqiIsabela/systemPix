package com.github.tqiIsabela.systemPix.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "pix_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long cpf;

    @Column(nullable = false)
    private String chave;

    @Column(nullable = false)
    private double valor;

    @Column(name = "data_hora_transacao", nullable = false)
    private LocalDateTime dataHoraTransacao;

    @Column(nullable = false)
    private String banco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_operacao", nullable = false)
    private TipoOperacao tipoOperacao;

    public enum TipoOperacao {
        RECEBIDO("Recebido"),
        ENVIADO("Enviado");

        private final String descricao;

        TipoOperacao(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}




