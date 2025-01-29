INSERT INTO Places (address,name) values ('Vogelweidplatz_14', 'Wiener Stadthalle');
INSERT INTO User_Roles(name) values ('ROLE_CUSTOMER');
INSERT INTO User_Roles(name) values ('ROLE_ADMIN');

INSERT INTO Users (name,email,phone,password, role_id) values ('Lev Pavelko', 'levpavelko@gmail.com', 0996589230, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '1');
INSERT INTO Users (name,email,phone,password, role_id) values ('Alex Muller', 'alexmuller@gmail.com', 0996589236, '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', '2');

INSERT INTO Customers (user_id) values (1);
INSERT INTO Admins (user_id) values (2);
