/** CLIENT **/
insert into client values 
	(1, 'Customer John Doe'),
	(2, 'Customer Jane Doe'),
	(3, 'Customer Jay Doe');

/** SELLER **/
insert into seller values 
	(1, 'Seller John Doe'),
	(2, 'Seller Jane Doe');

/** PRODUCT **/
insert into product values 
	(1, 'Product A', 0.75),
	(2, 'Product B', 5.25),
	(3, 'Product C', 6.0);

/** TRANSACTION **/
insert into transaction values 
	(1, curdate(), 1, 1, 1);

/** TRANSACTION DETAIL **/
insert into transaction_detail values 
	(1, 22, 1, 1),
	(2, 8, 2, 1),
	(3, 4, 3, 1);