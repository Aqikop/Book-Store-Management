-- CREATING DATABASE OBJECTS
-- AUTHOR
CREATE TABLE author (
    author_id VARCHAR(10),
    author_name VARCHAR(20),
    CONSTRAINT a_id_pk PRIMARY KEY (author_id)
);

-- PUBLISHER
CREATE TABLE publisher (
    publisher_id VARCHAR(10),
    publisher_name VARCHAR(50),
    CONSTRAINT pub_id_pk PRIMARY KEY (publisher_id)
);

-- BOOKS
CREATE TABLE books (
    book_id VARCHAR(10),
    book_name VARCHAR(50),
    author_id VARCHAR(10),
    price NUMERIC(10, 2),
    publisher_id VARCHAR(10),
    CONSTRAINT bk_id_pk PRIMARY KEY (book_id),
    CONSTRAINT bk_a_id_fk FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE CASCADE,
    CONSTRAINT bk_pub_id_fk FOREIGN KEY (publisher_id) REFERENCES publisher (publisher_id) ON DELETE CASCADE
);

-- SHIPPING TYPE
CREATE TABLE shipping_type (
    shipping_type VARCHAR(10),
    shipping_price NUMERIC(6, 2),
    CONSTRAINT st_pk PRIMARY KEY (shipping_type)
);

-- SHOPPING CART
CREATE TABLE shopping_cart (
    shopping_cart_id INTEGER,
    book_id VARCHAR(10),
    price NUMERIC(10, 2),
    shopping_date DATE,
    quantity INTEGER,
    CONSTRAINT sc_id_pk PRIMARY KEY (shopping_cart_id),
    CONSTRAINT sc_bk_id_fk FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE
);

-- CUSTOMER
CREATE TABLE customer (
    customer_id VARCHAR(6) NOT NULL,
    customer_name VARCHAR(40),
    street_address VARCHAR(50),
    city VARCHAR(25),
    zip VARCHAR(20),
    state VARCHAR(2),
    email_address VARCHAR(50),
    phone_number BIGINT CHECK (phone_number <= 99999999999),
    credit_card_number BIGINT NOT NULL CHECK (credit_card_number <= 9999999999999999),
    CONSTRAINT c_id_pk PRIMARY KEY (customer_id),
    CONSTRAINT c_email_uk UNIQUE (email_address)
);

CREATE TABLE customer_acc (
    customer_id VARCHAR(6) NOT NULL,
    email_address VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    CONSTRAINT cc_id_fk FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    CONSTRAINT cc_email_uk UNIQUE (email_address)  -- Enforcing uniqueness on email
);

-- ORDER DETAILS
CREATE TABLE order_details (
    order_id INTEGER,
    customer_id VARCHAR(6),
    shipping_type VARCHAR(10),
    date_of_purchase DATE,
    shopping_cart_id INTEGER,
    CONSTRAINT od_id_pk PRIMARY KEY (order_id),
    CONSTRAINT od_c_id_fk FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE,
    CONSTRAINT od_st_fk FOREIGN KEY (shipping_type) REFERENCES shipping_type (shipping_type) ON DELETE CASCADE,
    CONSTRAINT od_sc_id_fk FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (shopping_cart_id) ON DELETE CASCADE
);
