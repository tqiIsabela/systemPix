-- db/migration/V3__create_user_table.sql
CREATE TABLE user (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   nome VARCHAR(255) NOT NULL,
   cpf BIGINT NOT NULL UNIQUE,
   email VARCHAR(255) NOT NULL,
   telefone VARCHAR(20) NOT NULL
);

