truncate table product cascade;
truncate table item cascade;


insert into product (id, name, description) values
(200, 'milk-shake', 'a beverage'),
(201, 'eggs', 'enjoyable eggs');


insert into item (id, name, price, quantity) values
(200, 'milk-shake', 20.00, 5),
(201, 'eggs', 50.00, 8);
