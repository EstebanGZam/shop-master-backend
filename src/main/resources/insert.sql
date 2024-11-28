-- Insertando roles
INSERT INTO roles (name) VALUES ('ADMINISTRADOR');
INSERT INTO roles (name) VALUES ('CLIENTE');

-- Insertando usuarios
INSERT INTO users (username, password, email, address) VALUES ('admin', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'admin@gmail.com', 'Cali');
INSERT INTO users (username, password, email, address) VALUES ('Juan Colonia', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'jcolonia@gmail.com', 'Bolo');
INSERT INTO users (username, password, email, address) VALUES ('Esteban Gaviria', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'egaviria@gmail.com', 'Palmira');
INSERT INTO users (username, password, email, address) VALUES ('Juan Diaz', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'jdiaz@gmail.com', 'Cali');
INSERT INTO users (username, password, email, address) VALUES ('Manuel Cardona', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'mcardona@gmail.com', 'Palmira');
INSERT INTO users (username, password, email, address) VALUES ('Juan Muñoz', '$2a$10$5NyK3ZMtkNHWhNWz.vLGcuwFYMjuoDUZBR4dgNZg7ywb7/H/x33py', 'jmuñoz@gmail.com', 'Cali');

-- Insertando los usuarios y sus roles en la tabla cruzada
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO user_role (user_id, role_id) VALUES (3, 2);
INSERT INTO user_role (user_id, role_id) VALUES (4, 2);
INSERT INTO user_role (user_id, role_id) VALUES (5, 2);
INSERT INTO user_role (user_id, role_id) VALUES (6, 2);

-- Insertando métodos de pago
INSERT INTO payment_methods (name) VALUES ('Tar. Crédito');
INSERT INTO payment_methods (name) VALUES ('Tar. Débito');

-- Insertando primera orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-03-15 10:30:00', 79.98, 4);

-- Detalle orden 1
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 49.99, 1, '001');

-- Detalle orden 1
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 29.99, 1, '002');

-- Detalles de pago con tarjeta de crédito para primera orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (123456, 'Esteban', 'Gaviria', '123', '2025-12-31 00:00:00', 1, 1);


-- Factura para primera orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-03-15 10:31:00', 79.98, 1);

-- Insertando segunda orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-03-20 15:45:00', 119.98, 3);

-- Detalle orden 2
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (2, 59.99, 2, '003');

-- Detalles de pago con tarjeta de débito para segunda orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (789012, 'Esteban', 'Gaviria', '456', '2026-06-30 00:00:00',2,2);

-- Factura para segunda orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-03-20 15:46:00', 119.98, 2);

-- Insertando tercera orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-04-05 09:00:00', 199.95, 2);

-- Detalle orden 3
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (3, 59.99, 3, '004');

-- Detalles de pago con tarjeta de crédito para tercera orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (123457, 'Juan', 'Colonia', '321', '2025-07-15 00:00:00', 3, 1);

-- Factura para tercera orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-04-05 09:01:00', 199.95, 3);

-- Insertando cuarta orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-04-12 11:15:00', 89.97, 4);

-- Detalle orden 4
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 59.99, 4, '005');

-- Detalles de pago con tarjeta de débito para cuarta orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (456789, 'Juan', 'Diaz', '654', '2026-08-30 00:00:00', 4, 2);

-- Factura para cuarta orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-04-12 11:16:00', 89.97, 4);

-- Insertando quinta orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-05-02 14:30:00', 159.95, 5);

-- Detalle orden 5
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (2, 79.97, 5, '006');

-- Detalles de pago con tarjeta de crédito para quinta orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (987654, 'Manuel', 'Cardona', '789', '2025-11-15 00:00:00', 5, 1);

-- Factura para quinta orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-05-02 14:31:00', 159.95, 5);

-- Insertando sexta orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-05-10 17:20:00', 69.99, 6);

-- Detalle orden 6
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 69.99, 6, '007');

-- Detalles de pago con tarjeta de débito para sexta orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (345678, 'Juan', 'Muñoz', '987', '2027-02-20 00:00:00', 6, 2);

-- Factura para sexta orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-05-10 17:21:00', 69.99, 6);

-- Insertando séptima orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-05-15 16:00:00', 109.99, 3);

-- Detalle orden 7
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (2, 54.99, 7, '008');

-- Detalles de pago con tarjeta de crédito para séptima orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (987654, 'Esteban', 'Gaviria', '654', '2026-10-31 00:00:00', 7, 1);

-- Factura para séptima orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-05-15 16:01:00', 109.99, 7);

-- Insertando octava orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-06-01 18:30:00', 199.95, 2);

-- Detalle orden 8
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (3, 66.65, 8, '009');

-- Detalles de pago con tarjeta de débito para octava orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (123456, 'Juan', 'Colonia', '321', '2025-09-10 00:00:00', 8, 2);

-- Factura para octava orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-06-01 18:31:00', 199.95, 8);

-- Insertando novena orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-06-10 10:45:00', 89.99, 4);

-- Detalle orden 9
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 89.99, 9, '010');

-- Detalles de pago con tarjeta de crédito para novena orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (123467, 'Juan', 'Diaz', '432', '2026-04-25 00:00:00', 9, 1);

-- Factura para novena orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-06-10 10:46:00', 89.99, 9);

-- Insertando décima orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-06-20 14:00:00', 149.97, 5);

-- Detalle orden 10
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (3, 49.99, 10, '011');

-- Detalles de pago con tarjeta de débito para décima orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (678901, 'Manuel', 'Cardona', '321', '2027-03-15 00:00:00', 10, 2);

-- Factura para décima orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-06-20 14:01:00', 149.97, 10);

-- Insertando undécima orden
INSERT INTO orders (order_date, total, user_id) VALUES ('2024-07-01 12:00:00', 79.99, 6);

-- Detalle orden 11
INSERT INTO order_details (quantity, unit_price, order_id, product_id) VALUES (1, 79.99, 11, '012');

-- Detalles de pago con tarjeta de crédito para undécima orden
INSERT INTO payment_details (card_number, cardholder_name, cardholder_lastname, security_code, expiration_date, order_id, payment_method_id) VALUES (234567, 'Juan', 'Muñoz', '987', '2026-12-10 00:00:00', 11, 1);

-- Factura para undécima orden
INSERT INTO invoices (issue_date, total, order_id) VALUES ('2024-07-01 12:01:00', 79.99, 11);

