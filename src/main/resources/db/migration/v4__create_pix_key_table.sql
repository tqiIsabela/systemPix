CREATE TABLE pix_key (
    id NUMERIC PRIMARY KEY AUTO_INCREMENT,
    cpf NUMERIC (11,0) NOT NULL,
    email VARCHAR(255),
    phone_number VARCHAR(50),
    random_key VARCHAR(50),
    account_id NUMERIC NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_cpf ON pix_key (cpf);
CREATE INDEX idx_email ON pix_key (email);
CREATE INDEX idx_phone_number ON pix_key (phone_number);
CREATE INDEX idx_random_key ON pix_key (random_key);
CREATE INDEX idx_account_id ON pix_key (account_id);

ALTER TABLE pix_key ADD FOREIGN KEY (account_id) REFERENCES
account_balance(id);