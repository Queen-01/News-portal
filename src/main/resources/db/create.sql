SET MODE PostgresSQL;
CREATE DATABASE news_portal;
CREATE TABLE department (id int PRIMARY KEY, name varchar, description varchar);
CREATE TABLE users ();
CREATE TABLE news ();
CREATE DATABASE news_portal_test WITH TEMPLATE news_portal;
