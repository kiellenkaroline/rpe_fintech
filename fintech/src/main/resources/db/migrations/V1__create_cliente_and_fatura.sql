CREATE TABLE cliente (
                         id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         cpf VARCHAR(14) UNIQUE NOT NULL,
                         data_nascimento DATE NOT NULL,
                         status_bloqueio CHAR(1) NOT NULL,
                         limite_credito DECIMAL(10,2) NOT NULL
);

CREATE TABLE fatura (
                        id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                        cliente_id BIGINT NOT NULL,
                        data_vencimento DATE NOT NULL,
                        data_pagamento DATE,
                        valor DECIMAL(10,2) NOT NULL,
                        status CHAR(1) NOT NULL,
                        CONSTRAINT fk_fatura_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);
