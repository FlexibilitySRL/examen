--Product Table
insert into product (id, date_created, name, deleted, price) values (1, now(), 'Switch', false, 300.01);
insert into product (id, date_created, name, deleted, price) values (2, now(), 'PS4 Pro', false, 400);
insert into product (id, date_created, name, deleted, price) values (3, now(), 'XBox One S', false, 350.00);
--Client Table
insert into client (id, date_created, document_id, name, deleted) values (1, now(), '123-456', 'Manuel', false);
insert into client (id, date_created, document_id, name, deleted) values (2, now(), '145-123', 'Maria', false);
insert into client (id, date_created, document_id, name, deleted) values (3, now(), '199-288', 'Fabiola', false);
--Transaction Table
insert into incoming_transaction (id, transaction_id, date_created, product_id, client_id, amount) values (1, 1, now(), 1, 1, 200.00);
insert into incoming_transaction (id, transaction_id, date_created, product_id, client_id, amount) values (2, 1, now(), 2, 1, 100.00);
insert into incoming_transaction (id, transaction_id, date_created, product_id, client_id, amount) values (3, 2, now(), 3, 2, 300.00);