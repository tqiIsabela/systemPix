CREATE TABLE pix_key (
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   cpf BIGINT NOT NULL,
   email VARCHAR(255),
   phone_number VARCHAR(50),
   random_key VARCHAR(50),
   account_id BIGINT NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   UNIQUE KEY idx_cpf (cpf),
   UNIQUE KEY idx_email (email),
   UNIQUE KEY idx_phone_number (phone_number),
   UNIQUE KEY idx_random_key (random_key),
   KEY idx_account_id (account_id),
   FOREIGN KEY (account_id) REFERENCES account_balance(id)
);
