-- db/migration/V3__create_user_table.sql
CREATE TABLE user (
    id NUMERIC PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf NUMERIC (11,0) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);
