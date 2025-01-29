
CREATE TABLE IF NOT EXISTS Places(
    id SERIAL PRIMARY KEY,
    address varchar(30),
    name varchar(30)
);
CREATE TABLE IF NOT EXISTS Events (
    id SERIAL PRIMARY KEY,
    event_date date,
    name varchar(30)
);

CREATE TABLE IF NOT EXISTS Customers(
    id SERIAL PRIMARY KEY

);
CREATE TABLE IF NOT EXISTS Admins(
    id SERIAL PRIMARY KEY

);

CREATE TABLE IF NOT EXISTS User_Roles(
    id SERIAL PRIMARY KEY,
    name varchar (30)

);

CREATE TYPE ticket_status AS ENUM ('FREE', 'SOLD');

CREATE TABLE IF NOT EXISTS Tickets (
     id SERIAL PRIMARY KEY,
     cost integer,
     number integer,
     status ticket_status


);

CREATE TABLE IF NOT EXISTS Users(
    id SERIAL PRIMARY KEY,
    name varchar (30),
    email varchar(30),
    phone integer,
    password varchar(70)


);
