CREATE DATABASE IF NOT EXISTS library;

USE library;

TRUNCATE TABLE books;
TRUNCATE TABLE issuebooks;
TRUNCATE TABLE librarian;

CREATE TABLE IF NOT EXISTS books (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  callno VARCHAR(100) UNIQUE NOT NULL,
  name VARCHAR(100) NOT NULL,
  author VARCHAR(100) NOT NULL,
  publisher VARCHAR(100) NOT NULL,
  quantity INT NOT NULL,
  issued INT NOT NULL DEFAULT 0,
  added_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO books(callno,name,author, publisher, quantity, issued, added_date) VALUES
( 'A@4', 'C In Depth', 'Shrivastav', 'BPB', 2, 2, '2016-07-19 19:37:56'),
( 'B@1', 'DBMS', 'Korth', 'Pearson', 3, 0, '2016-07-18 18:39:52'),
( 'G@12', 'Let''s see', 'Yashwant Kanetkar', 'BPB', 10, 0, '2016-07-18 23:02:14');





CREATE TABLE IF NOT EXISTS issuebooks (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  bookcallno VARCHAR(50) NOT NULL,
  studentid INT  NOT NULL,
  studentname VARCHAR(50) NOT NULL,
  studentcontact VARCHAR(20) NOT NULL,
  issueddate timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO issuebooks (bookcallno, studentid, studentname, studentcontact, issueddate) VALUES
( 'A@4', 23, 'kk', '932992932', '2016-07-19 18:43:16'),
( 'A@4', 335, 'Sumedh', '95676565756', '2016-07-19 18:44:34'),
( 'A@4', 87, 'abhishek', '9329882382', '2016-07-19 18:46:12');


CREATE TABLE IF NOT EXISTS librarian (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  address VARCHAR(200) NOT NULL,
  city VARCHAR(100) NOT NULL,
  contact VARCHAR(20) NOT NULL
);

INSERT INTO librarian (name, password, email, address, city, contact) VALUES
('Prabhakar', 'ppp', 'prabhakar@gmail.com', 'javatpoint', 'noida', '9998328238'),
( 'sumedh', 'sumesh', 'sumesh@gmail.com', 'Kuch Bhi', 'noida', '93823932823'),
( 'abhi', 'abhi', 'abhi@gmail.com', 'javatpoint', 'noida', '92393282323');
