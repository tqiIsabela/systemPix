-- Criação da tabela de transações Pix
CREATE TABLE pix_transaction (
    id NUMERIC PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf NUMERIC(11,0) NOTNULL,
    chave VARCHAR(255) NOT NULL,
    valor DOUBLE NOT NULL,
    data_hora_transacao TIMESTAMP NOT NULL,
    banco VARCHAR(255) NOT NULL,
    tipo_operacao VARCHAR(50) NOT NULL
);

CREATE INDEX idx_chave ON pix_transaction (chave);
