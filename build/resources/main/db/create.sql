SET MODE PostgresSQL;
CREATE DATABASE news_portal;
CREATE TABLE department (id int PRIMARY KEY, name varchar, description varchar);
CREATE TABLE users (id int PRIMARY KEY, name varchar, role varchar, position varchar, departId int);
CREATE TABLE news (id int PRIMARY KEY, userId int, content varchar,posdate timestamp, departId int, type varchar);
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
