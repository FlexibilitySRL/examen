insert into invoices(id, invoice_date, status, subtotal, customer_id)
 values(nextval('seq_invoice'), current_timestamp, 'APPROVED', 18000, 1);

 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 1, 1, 1);
 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 1, 1, 3);

-------------------------------------------------------------------------
insert into invoices(id, invoice_date, status, subtotal, customer_id)
 values(nextval('seq_invoice'), current_timestamp, 'APPROVED', 96000, 2);

 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 2, 2, 3);
 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 1, 2, 4);

--------------------------------------------------------------------------
insert into invoices(id, invoice_date, status, subtotal, customer_id)
 values(nextval('seq_invoice'), current_timestamp, 'APPROVED', 97000, 1);

 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 1, 3, 4);
 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 1, 3, 2);

--------------------------------------------------------------------------
insert into invoices(id, invoice_date, status, subtotal, customer_id)
 values(nextval('seq_invoice'), current_timestamp, 'DRAFT', 120000, 3);

 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 2, 4, 1);
 insert into invoice_items(id, quantity, invoice_id, product_id)
  values(nextval('seq_invoice_item'), 1, 4, 4);