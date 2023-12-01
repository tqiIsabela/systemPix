-- Criação da tabela de saldo de conta
CREATE TABLE account_balance (
    id NUMERIC PRIMARY KEY AUTO_INCREMENT,
    cpf NUMERIC(11,0) PRIMARY KEY, -- A chave primária é o CPF
    saldo DOUBLE NOT NULL,
    FOREIGN KEY (cpf) REFERENCES pix_transaction(cpf)
);
