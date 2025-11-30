-- Temporarily disable foreign key checks
SET session_replication_role = 'replica';
-- ===============================
-- CUSTOMER
-- ===============================
INSERT INTO customer(customer_id, customer_name, street_address, city, zip, state, email_address, phone_number, credit_card_number)
VALUES
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'David', 'Jamir Lane', 'Mangalore', '580254', 'BN', 'david@oracle.com', 8457692461, 6547981236453287),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Ramanuj', 'Jamir Lane', 'Mangalore', '580254', 'BN', 'ramanuj@oracle.com', 7894563271, 7895314521795463),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Venkatesh', 'Ho Chin Minh', 'JP Nagar', '580254', 'BN', 'venky@oracle.com', 5412975236, 6847925814236541),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Subramanian', 'Paratha Gali', 'Whitefield', '580254', 'BN', 'subram@oracle.com', 4536578925, 4236152819743652),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Vikram', 'Khaogali', 'Whitefield', '580254', 'BN', 'vikram@oracle.com', 1453698258, 2459736814563871),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'John Smith', '42 Elm Street', 'Boston', '02108', 'MA', 'john.smith@email.com', 6175551020, 4532987412564785),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Emily Johnson', '85 Sunset Blvd', 'Los Angeles', '90026', 'CA', 'emily.johnson@email.com', 2135558745, 5489123647891256),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Michael Brown', '19 River Road', 'Chicago', '60601', 'IL', 'michael.brown@email.com', 3125559630, 4536987451236547),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Sarah Miller', '78 King Street', 'Seattle', '98101', 'WA', 'sarah.miller@email.com', 2065557412, 5124789653214785),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'James Wilson', '8 Park Avenue', 'New York', '10016', 'NY', 'james.wilson@email.com', 9175558569, 4536127896523145),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Olivia Davis', '99 Lakeview Road', 'Toronto', 'M4B1B3', 'ON', 'olivia.davis@email.com', 4165552847, 5123698745216985),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Liam Taylor', '23 Willow Lane', 'Dublin', 'D08VF85', 'DB', 'liam.taylor@email.com', 35315551234, 4532978541236987),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Sophia Anderson', '13 Oak Drive', 'Auckland', '1010', 'AK', 'sophia.anderson@email.com', 6495558745, 4712369854125369),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Ethan Thomas', '6 Maple Street', 'Sydney', '2000', 'NS', 'ethan.thomas@email.com', 6125557845, 4536987412589632),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Ava Martin', '58 Queen Street', 'London', 'W1D3QE', 'LD', 'ava.martin@email.com', 4475559632, 5236987412598741),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Noah White', '14 Hilltop Avenue', 'Glasgow', 'G12 8QQ', 'SC', 'noah.white@email.com', 4414155598, 4536987412598745),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Mia Harris', '7 Pine Street', 'Vancouver', 'V6B1A1', 'BC', 'mia.harris@email.com', 6045557485, 4536987412587412),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'William Clark', '21 Ocean Drive', 'Miami', '33101', 'FL', 'william.clark@email.com', 3055558745, 4512369854125367),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Isabella Lewis', '11 Garden Street', 'Adelaide', '5000', 'SA', 'isabella.lewis@email.com', 6185559632, 4536987412589635),
('c' || LPAD(nextval('cust_id_seq')::text, 4, '0'), 'Henry Walker', '44 Brook Lane', 'Manchester', 'M13 9PL', 'UK', 'henry.walker@email.com', 4416155587, 4536987412365894);


-- ===============================
-- AUTHOR
-- ===============================
INSERT INTO author(author_id, author_name)
VALUES
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Chetan Bhagat'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Jerome'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Nancy'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Krishnamurthy'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Salvador'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'J.K. Rowling'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'George R.R. Martin'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Stephen King'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Jane Austen'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Mark Twain'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'J.R.R. Tolkien'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Agatha Christie'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Ernest Hemingway'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'F. Scott Fitzgerald'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Harper Lee'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'John Steinbeck'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Arthur Conan Doyle'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Lewis Carroll'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'Roald Dahl'),
('A' || LPAD(nextval('auth_id_seq')::text, 4, '0'), 'George Orwell');



-- ===============================
-- PUBLISHER
-- ===============================
INSERT INTO publisher(publisher_id, publisher_name)
VALUES
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'PENGUIN PUBLISHERS'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'WB PUBLISHERS'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'LAYMAN PUBLISHERS'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'DEAD PUBLISHERS'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'ROCKSTAR PUBLISHERS'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Bloomsbury Publishing'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'HarperCollins'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Simon & Schuster'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Hachette Book Group'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Random House'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Macmillan Publishers'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Scholastic Press'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Pan Macmillan'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Vintage Books'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Oxford University Press'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Penguin Random House'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Harvill Secker'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Knopf Doubleday'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Tor Books'),
('P' || LPAD(nextval('pub_id_seq')::text, 4, '0'), 'Little, Brown and Company');



-- ===============================
-- SHIPPING TYPE
-- ===============================
INSERT INTO shipping_type(shipping_type, shipping_price)
VALUES
('STANDARD', 13.99),
('EXPRESS', 29.99);


-- ===============================
-- BOOKS
-- ===============================
INSERT INTO books(book_id, book_name, author_id, price, publisher_id)
VALUES
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'),
 'Musings and More',
 (SELECT author_id FROM author WHERE author_name = 'Chetan Bhagat'),
197.00,
 (SELECT publisher_id FROM publisher WHERE publisher_name = 'PENGUIN PUBLISHERS')),

('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'),
 'Akbar and Birbal',
 (SELECT author_id FROM author WHERE author_name = 'Nancy'),
528.00,
 (SELECT publisher_id FROM publisher WHERE publisher_name = 'LAYMAN PUBLISHERS')),

('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'),
 'Two States',
 (SELECT author_id FROM author WHERE author_name = 'Chetan Bhagat'),
 528.00,
 (SELECT publisher_id FROM publisher WHERE publisher_name = 'PENGUIN PUBLISHERS')),

('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'),
 '3 Men in a Boat',
 (SELECT author_id FROM author WHERE author_name = 'Jerome'),
 499.00,
 (SELECT publisher_id FROM publisher WHERE publisher_name = 'PENGUIN PUBLISHERS')),

('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'),
 'Pirates',
 (SELECT author_id FROM author WHERE author_name = 'Salvador'),
 399.99,
 (SELECT publisher_id FROM publisher WHERE publisher_name = 'DEAD PUBLISHERS')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Harry Potter and the Sorcerer''s Stone', (SELECT author_id FROM author WHERE author_name='J.K. Rowling'), 699.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Bloomsbury Publishing')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'A Game of Thrones', (SELECT author_id FROM author WHERE author_name='George R.R. Martin'), 899.99, (SELECT publisher_id FROM publisher WHERE publisher_name='HarperCollins')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'The Shining', (SELECT author_id FROM author WHERE author_name='Stephen King'), 499.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Hachette Book Group')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Pride and Prejudice', (SELECT author_id FROM author WHERE author_name='Jane Austen'), 299.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Random House')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'The Hobbit', (SELECT author_id FROM author WHERE author_name='J.R.R. Tolkien'), 599.99, (SELECT publisher_id FROM publisher WHERE publisher_name='HarperCollins')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'The Adventures of Tom Sawyer', (SELECT author_id FROM author WHERE author_name='Mark Twain'), 249.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Macmillan Publishers')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Murder on the Orient Express', (SELECT author_id FROM author WHERE author_name='Agatha Christie'), 399.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Harvill Secker')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'To Kill a Mockingbird', (SELECT author_id FROM author WHERE author_name='Harper Lee'), 349.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Vintage Books')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Animal Farm', (SELECT author_id FROM author WHERE author_name='George Orwell'), 199.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Penguin Random House')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'The Great Gatsby', (SELECT author_id FROM author WHERE author_name='F. Scott Fitzgerald'), 249.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Simon & Schuster')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Charlie and the Chocolate Factory', (SELECT author_id FROM author WHERE author_name='Roald Dahl'), 299.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Scholastic Press')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Alice in Wonderland', (SELECT author_id FROM author WHERE author_name='Lewis Carroll'), 199.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Oxford University Press')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'The Old Man and the Sea', (SELECT author_id FROM author WHERE author_name='Ernest Hemingway'), 199.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Little, Brown and Company')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'The Catcher in the Rye', (SELECT author_id FROM author WHERE author_name='F. Scott Fitzgerald'), 289.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Knopf Doubleday')),
('BK' || LPAD(nextval('book_id_seq')::text, 4, '0'), 'Of Mice and Men', (SELECT author_id FROM author WHERE author_name='John Steinbeck'), 219.99, (SELECT publisher_id FROM publisher WHERE publisher_name='Tor Books'));



-- ===============================
-- SHOPPING CART
-- ===============================
INSERT INTO shopping_cart(shopping_cart_id, book_id, price, shopping_date, quantity)
VALUES
(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Pirates'),
 (SELECT price * 2 FROM books WHERE book_name = 'Pirates'),
 TO_DATE('12-02-2019', 'DD-MM-YYYY'),
 2),

(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = '3 Men in a Boat'),
 (SELECT price * 3 FROM books WHERE book_name = '3 Men in a Boat'),
 TO_DATE('14-02-2019', 'DD-MM-YYYY'),
 3),

(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Musings and More'),
 (SELECT price * 3 FROM books WHERE book_name = 'Musings and More'),
 TO_DATE('18-02-2019', 'DD-MM-YYYY'),
 3),

(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Two States'),
 (SELECT price * 4 FROM books WHERE book_name = 'Two States'),
 TO_DATE('15-02-2019', 'DD-MM-YYYY'),
 4),

(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Musings and More'),
 (SELECT price * 2 FROM books WHERE book_name = 'Musings and More'),
 TO_DATE('20-02-2019', 'DD-MM-YYYY'),
 2),

-- Harry Potter (2 copies)
(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Harry Potter and the Sorcerer''s Stone'),
 (SELECT price * 2 FROM books WHERE book_name = 'Harry Potter and the Sorcerer''s Stone'),
 TO_DATE('22-02-2019', 'DD-MM-YYYY'),
 2),

-- Pride & Prejudice (1 copy)
(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Pride and Prejudice'),
 (SELECT price * 1 FROM books WHERE book_name = 'Pride and Prejudice'),
 TO_DATE('25-02-2019', 'DD-MM-YYYY'),
 1),

-- The Hobbit (4 copies)
(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'The Hobbit'),
 (SELECT price * 4 FROM books WHERE book_name = 'The Hobbit'),
 TO_DATE('26-02-2019', 'DD-MM-YYYY'),
 4),

-- Animal Farm (3 copies)
(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'Animal Farm'),
 (SELECT price * 3 FROM books WHERE book_name = 'Animal Farm'),
 TO_DATE('28-02-2019', 'DD-MM-YYYY'),
 3),

-- The Shining (1 copy)
(nextval('shop_cart_id_seq'),
 (SELECT book_id FROM books WHERE book_name = 'The Shining'),
 (SELECT price * 1 FROM books WHERE book_name = 'The Shining'),
 TO_DATE('01-03-2019', 'DD-MM-YYYY'),
 1);




-- ===============================
-- ORDER DETAILS
-- ===============================
INSERT INTO order_details(order_id, customer_id, shipping_type, date_of_purchase, shopping_cart_id)
VALUES
(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'David'),
 'STANDARD',
 (SELECT shopping_date FROM shopping_cart WHERE quantity = 2 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart WHERE quantity = 2 LIMIT 1)),

(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Venkatesh'),
 'EXPRESS',
 (SELECT shopping_date FROM shopping_cart WHERE quantity = 3 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart WHERE quantity = 3 LIMIT 1)),

(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Vikram'),
 'STANDARD',
 (SELECT shopping_date FROM shopping_cart WHERE quantity = 4 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart WHERE quantity = 4 LIMIT 1)),

(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Ramanuj'),
 'STANDARD',
 (SELECT shopping_date FROM shopping_cart WHERE quantity = 3 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart WHERE quantity = 3 LIMIT 1)),

(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Ramanuj'),
 'EXPRESS',
 (SELECT shopping_date FROM shopping_cart WHERE quantity = 2 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart WHERE quantity = 2 LIMIT 1)),

-- David buys Harry Potter (2 copies)
(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'David'),
 'EXPRESS',
 (SELECT shopping_date FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 4 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 4 LIMIT 1)),

-- Emily Johnson buys Pride & Prejudice
(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Emily Johnson'),
 'STANDARD',
 (SELECT shopping_date FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 3 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 3 LIMIT 1)),

-- Michael Brown buys The Hobbit
(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Michael Brown'),
 'STANDARD',
 (SELECT shopping_date FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 2 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 2 LIMIT 1)),

-- Sarah Miller buys Animal Farm
(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'Sarah Miller'),
 'EXPRESS',
 (SELECT shopping_date FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 1 LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart ORDER BY shopping_cart_id DESC OFFSET 1 LIMIT 1)),

-- James Wilson buys The Shining
(nextval('od_id_seq'),
 (SELECT customer_id FROM customer WHERE customer_name = 'James Wilson'),
 'STANDARD',
 (SELECT shopping_date FROM shopping_cart ORDER BY shopping_cart_id DESC LIMIT 1),
 (SELECT shopping_cart_id FROM shopping_cart ORDER BY shopping_cart_id DESC LIMIT 1));








-- ===============================
-- CUSTOMER ACC
-- ===============================

INSERT INTO customer_acc (customer_id, email_address, password)
VALUES
  ((SELECT customer_id FROM customer WHERE email_address = 'david@oracle.com'), 'david@oracle.com', 'david123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'ramanuj@oracle.com'), 'ramanuj@oracle.com', 'ramanu123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'venky@oracle.com'), 'venky@oracle.com', 'venky123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'subram@oracle.com'), 'subram@oracle.com', 'subram123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'vikram@oracle.com'), 'vikram@oracle.com', 'vikram123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'john.smith@email.com'), 'john.smith@email.com', 'john123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'emily.johnson@email.com'), 'emily.johnson@email.com', 'emily123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'michael.brown@email.com'), 'michael.brown@email.com', 'michael123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'sarah.miller@email.com'), 'sarah.miller@email.com', 'sarah123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'james.wilson@email.com'), 'james.wilson@email.com', 'james123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'olivia.davis@email.com'), 'olivia.davis@email.com', 'olivia123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'liam.taylor@email.com'), 'liam.taylor@email.com', 'liam123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'sophia.anderson@email.com'), 'sophia.anderson@email.com', 'sophia123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'ethan.thomas@email.com'), 'ethan.thomas@email.com', 'ethan123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'ava.martin@email.com'), 'ava.martin@email.com', 'ava123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'noah.white@email.com'), 'noah.white@email.com', 'noah123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'mia.harris@email.com'), 'mia.harris@email.com', 'mia123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'william.clark@email.com'), 'william.clark@email.com', 'william123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'isabella.lewis@email.com'), 'isabella.lewis@email.com', 'isabella123'),
  ((SELECT customer_id FROM customer WHERE email_address = 'henry.walker@email.com'), 'henry.walker@email.com', 'henry123');



-- ===============================
-- Re-enable foreign key checks
-- ===============================
SET session_replication_role = 'origin';

COMMIT;
