-- Criação da tabela de saldo de conta
CREATE TABLE account_balance (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   cpf BIGINT UNIQUE NOT NULL,
   saldo DOUBLE NOT NULL,
   FOREIGN KEY (cpf) REFERENCES pix_transaction(cpf)
);
