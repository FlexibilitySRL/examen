--Product Table
insert into product (id, date_created, name, deleted, price) values (1, now(), 'Switch', false, 300.01);
insert into product (id, date_created, name, deleted, price) values (2, now(), 'PS4 Pro', false, 400);
insert into product (id, date_created, name, deleted, price) values (3, now(), 'XBox One S', false, 350.00);
--Client Table
insert into client (id, date_created, document_id, name, deleted) values (1, now(), '123-456', 'Manuel', false);