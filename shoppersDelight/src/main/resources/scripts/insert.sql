truncate table product cascade;
truncate table item cascade;
truncate table customer cascade;
-- truncate table customer_order cascade;
truncate table cart cascade;
truncate table cart_items cascade;

insert into product (id, name, description,quantity) values
(203, 'milk-shake', 'a beverage',20),
(204, 'eggs', 'enjoyable eggs',30);


insert into item (id, name, price, quantity) values
(200, 'milk-shake', 20.00, 5),
(201, 'eggs', 50.00, 8),
(202, 'eggs', 50.00, 4),
(203, 'pen', 50.00, 2);

insert into cart (id) values
(200),
(201);

insert into cart_items (cart_id, items_id) values
(200, 200),
(200, 201),
(201, 202),
(201, 203);

insert into customer (id, email, password, cart_id) values
(100, 'test@email.com', 'password', 200),
(101, 'jonsnow@email.com', 'password', 201);
--
-- insert into customer_order (id, delivery_address, phone_number, customer_id, amount) values
-- (100, '312, Herbert Macaulay way', '09023456789',100, 20000),
-- (101, '313, Herbert Macaulay way', '09023456789',100, 20000);