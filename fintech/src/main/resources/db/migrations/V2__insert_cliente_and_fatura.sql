INSERT INTO cliente (id, nome, cpf, data_nascimento, status_bloqueio, limite_credito) VALUES
(1, 'João da Silva', '123.456.789-00', '1985-07-09', 'A', 5000.00),
(2, 'Maria Oliveira', '987.654.321-00', '1990-01-15', 'B', 3000.00),
(3, 'Carlos Pereira', '111.222.333-44', '1978-03-22', 'A', 7000.00),
(4, 'Ana Costa', '555.666.777-88', '1995-11-30', 'A', 4500.00),
(5, 'Pedro Alves', '999.888.777-66', '1982-09-14', 'B', 3500.00),
(6, 'Luciana Souza', '444.555.666-77', '1993-05-10', 'A', 4000.00),
(7, 'Ricardo Lima', '222.333.444-55', '1988-12-01', 'A', 6000.00),
(8, 'Mariana Rocha', '777.888.999-00', '1991-07-07', 'B', 3200.00),
(9, 'Bruno Martins', '333.444.555-66', '1975-02-18', 'A', 8000.00),
(10, 'Fernanda Dias', '666.777.888-99', '1987-04-25', 'A', 5500.00);

INSERT INTO fatura (id, cliente_id, data_vencimento, data_pagamento, valor, status) VALUES
(1, 1, '2025-07-10', '2025-07-05', 1200.00, 'P'),
(2, 1, '2025-08-10', NULL, 1300.00, 'B'),
(3, 2, '2025-07-01', NULL, 900.00, 'A'),
(4, 3, '2025-07-15', '2025-07-14', 2000.00, 'P'),
(5, 4, '2025-07-20', NULL, 1500.00, 'B'),
(6, 5, '2025-07-25', '2025-07-23', 1700.00, 'P'),
(7, 6, '2025-08-05', NULL, 1100.00, 'A'),
(8, 7, '2025-08-10', '2025-08-09', 1900.00, 'P'),
(9, 8, '2025-08-15', NULL, 1400.00, 'B'),
(10, 9, '2025-08-20', NULL, 2100.00, 'A');
