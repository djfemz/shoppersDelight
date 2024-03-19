truncate table product cascade;
truncate table item cascade;
truncate table customer cascade;

insert into product (id, name, description,quantity) values
(203, 'milk-shake', 'a beverage',20),
(204, 'eggs', 'enjoyable eggs',30);


insert into item (id, name, price, quantity) values
(200, 'milk-shake', 20.00, 5),
(201, 'eggs', 50.00, 8);

insert into customer (id, email, password) values
(100, 'test@email.com', 'password'),
(101, 'jonsnow@email.com', 'password');