CREATE database IF NOT EXISTS market;

USE market;

DROP TABLE IF EXISTS bank_cards;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS product_types;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_statuses;
DROP TABLE IF EXISTS feeds_and_other;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS product_statuses;
DROP TABLE IF EXISTS confirmation_email_сodes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles; 


CREATE TABLE IF NOT EXISTS roles (
    id TINYINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name ENUM('WAITING_CODE_REGISTRATION', 'USER', 'ADMIN') DEFAULT NULL
)  ENGINE=INNODB;

INSERT INTO roles(name) 
VALUES('WAITING_CODE_REGISTRATION'), ('USER'), ('ADMIN');

CREATE TABLE IF NOT EXISTS users (
    id BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    roles_id TINYINT UNSIGNED NOT NULL DEFAULT 1,
    INDEX roles_ind (roles_id),
    FOREIGN KEY (roles_id)
        REFERENCES roles (id),
    email VARCHAR(255) DEFAULT NULL,
    is_confirmation_email BOOLEAN DEFAULT FALSE,
    login VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    discount DECIMAL(5,2) DEFAULT 0,
    is_active boolean default true,
    date_create DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS pets (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    image_path varchar(255) default null,
    specie VARCHAR(255),
    breed VARCHAR(255),
    birth_date DATE,
    price DECIMAL(65,2) UNSIGNED,
    discount DECIMAL(5,2) DEFAULT 0,
    number_of_units_products BIGINT DEFAULT 0,
    date_update DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS feeds_and_other (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    image_path varchar(255) default null,
    type VARCHAR(255),
    brand VARCHAR(255),
    description VARCHAR(255),
    pet_type VARCHAR(255) NOT NULL DEFAULT 'all',
    FULLTEXT ( pet_type ),
    price DECIMAL(65,2) UNSIGNED,
    discount DECIMAL(5,2) DEFAULT 0,
    number_of_units_products BIGINT DEFAULT 0,
    date_update DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS order_statuses (
    id TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name ENUM('OPEN', 'WAITING_PAY', 'CLOSED')
);

INSERT INTO order_statuses(name)
VALUES('OPEN'), ('WAITING_PAY'), ('CLOSED');

CREATE TABLE IF NOT EXISTS orders (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    users_id BIGINT UNSIGNED NOT NULL,
    INDEX users_ind (users_id),
    FOREIGN KEY (users_id)
        REFERENCES users (id),
    total_payment_amount DECIMAL(65 , 2 ) UNSIGNED NOT NULL DEFAULT 0,
    total_products_discount_amount DECIMAL(65 , 2 ) UNSIGNED NOT NULL DEFAULT 0,
    total_person_discount_amount DECIMAL(65 , 2 ) UNSIGNED NOT NULL DEFAULT 0,
    total_discount_amount DECIMAL(65 , 2 ) UNSIGNED NOT NULL DEFAULT 0,
    total_payment_with_discount_amount DECIMAL(65 , 2 ) UNSIGNED NOT NULL DEFAULT 0,
    date DATETIME DEFAULT NOW(),
    order_statuses_id TINYINT UNSIGNED NOT NULL DEFAULT 1,
    INDEX order_statuses_id (order_statuses_id),
    FOREIGN KEY (order_statuses_id)
        REFERENCES order_statuses (id)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS product_types (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
)  ENGINE=INNODB;

INSERT INTO product_types(name)
VALUES ('PETS'), ('FEEDS_AND_OTHER');

CREATE TABLE IF NOT EXISTS order_products (
    orders_id BIGINT UNSIGNED,
    INDEX orders_ind (orders_id),
    FOREIGN KEY (orders_id)
        REFERENCES orders (id),
    product_types_id BIGINT UNSIGNED,
    INDEX product_types_ind (product_types_id),
    FOREIGN KEY (product_types_id)
        REFERENCES product_types (id),
    pets_id BIGINT UNSIGNED,
    INDEX pets_ind (pets_id),
    FOREIGN KEY (pets_id)
        REFERENCES pets (id),
    feeds_and_other_id BIGINT UNSIGNED,
    INDEX feeds_and_other_ind (feeds_and_other_id),
    FOREIGN KEY (feeds_and_other_id)
        REFERENCES feeds_and_other (id)
);

CREATE TABLE IF NOT EXISTS confirmation_email_сodes (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    users_id BIGINT UNSIGNED NOT NULL,
    INDEX users_id_ind (users_id),
    FOREIGN KEY (users_id)
        REFERENCES users (id),
    code VARCHAR(100) NOT NULL,
    is_open BOOLEAN DEFAULT TRUE,
    open_date_time DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS bank_cards (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    number VARCHAR(20) NOT NULL,
    month_end TINYINT NOT NULL,
    year_end TINYINT NOT NULL,
    cvc SMALLINT NOT NULL,
    sum DECIMAL DEFAULT 0
)  ENGINE=INNODB;

INSERT INTO bank_cards(number, month_end, year_end, cvc, sum)
VALUES ('1478 5236 9632 5874', 04, 26, 123, 20), ('9876 5412 3654 7896', 06, 28, 987, 2000), ('9632 5874 1258 9632', 01, 23, 654, 0); 