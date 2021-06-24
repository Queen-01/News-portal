SET MODE PostgresSQL;
CREATE DATABASE news_portal;
CREATE TABLE IF NOT EXISTS department (
    id serial PRIMARY KEY,
    name varchar,
    description varchar
);
CREATE TABLE IF NOT EXISTS users (
     id serial PRIMARY KEY,
    name varchar,
    role varchar,
    position varchar,
    departId int
);
CREATE TABLE IF NOT EXISTS news (
    id serial PRIMARY KEY,
    userId int,
    content varchar,
    posdate timestamp,
    departId int,
    type varchar
);
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
