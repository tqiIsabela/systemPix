-- Criação da tabela de transações Pix
CREATE TABLE pix_transaction (
   id BIGINT PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(255) NOT NULL,
   cpf BIGINT NOT NULL,
   chave VARCHAR(255) NOT NULL,
   valor DOUBLE NOT NULL,
   data_hora_transacao TIMESTAMP NOT NULL,
   banco VARCHAR(255) NOT NULL,
   tipo_operacao VARCHAR(50) NOT NULL,
   UNIQUE KEY (cpf) -- Define o campo cpf como uma chave única
);

CREATE INDEX idx_chave ON pix_transaction (chave);

