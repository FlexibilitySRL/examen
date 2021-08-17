INSERT INTO paymenttype ( id, name ) VALUES
(1, 'Cash'),
(2, 'Card');

INSERT INTO transactionstatus ( id, name ) VALUES
(1, 'Approved'),
(2, 'Rejected'),
(3, 'Pending');

INSERT INTO buyer ( id, name, lastname, email, document ) VALUES
(1, 'Buyer', 'Dummy', 'dummybuyer@dummy.com', '111111111');

INSERT INTO seller ( id, fullname, contactemail ) VALUES
(1, 'Seller Company', 'dummyseller@dummy.com');

INSERT INTO product ( id, name, description, price, stock, fk_seller ) VALUES
(1, 'Pencil', 'Dummy product', 5.05, 10, 1),
(2, 'Eraser', 'Dummy product', 10.20, 20, 1);

INSERT INTO transaction ( id, date, amount, fk_buyer, fk_seller, fk_paymenttype, fk_transactionstatus ) VALUES
(1, parsedatetime('14-08-2021 18:47', 'dd-MM-yyyy hh:mm'), 20.30, 1, 1, 1, 1),
(2, parsedatetime('14-08-2021 19:47', 'dd-MM-yyyy hh:mm'), 30.60, 1, 1, 2, 2),
(3, parsedatetime('14-08-2021 20:47', 'dd-MM-yyyy hh:mm'), 5.05, 1, 1, 2, 3);

INSERT INTO transactiondetail ( id, quantity, fk_transaction, fk_product ) VALUES
(1, 2, 1, 1),
(2, 1, 1, 2),
(3, 3, 2, 2),
(4, 1, 3, 1);