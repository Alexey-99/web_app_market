CREATE database IF NOT EXISTS market;

USE market;

DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS product_types;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_statuses;
DROP TABLE IF EXISTS feeds_and_other;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS product_statuses;
DROP TABLE IF EXISTS verificate_сodes;
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
    name VARCHAR(100),
    surname VARCHAR(100),
    roles_id TINYINT UNSIGNED NOT NULL DEFAULT 1,
    INDEX roles_ind (roles_id),
    FOREIGN KEY (roles_id)
        REFERENCES roles (id),
    email VARCHAR(100) DEFAULT NULL,
	login VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    discount TINYINT DEFAULT 0,
    date_create DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

INSERT INTO users(name, surname, login, password, roles_id)
VALUES('Alex', 'Koroza', 'alex99', '123', 3), ('Tom', 'Jen', 'tom2', '12', 2), ('Anna', 'Pop', 'anna94', '1994', 1), ('Lie', 'To', 'lie97', '123456', 2);

CREATE TABLE product_statuses (
    id TINYINT UNSIGNED PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name ENUM('AVAILABLE', 'RESERVED', 'NOT_AVAILABLE')
);

INSERT INTO product_statuses(name)
VALUES('AVAILABLE'), ('RESERVED'), ('NOT_AVAILABLE');

CREATE TABLE IF NOT EXISTS pets (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    specie VARCHAR(150),
    breed VARCHAR(150),
    birth_date DATE,
    price DECIMAL UNSIGNED,
    status_id TINYINT UNSIGNED NOT NULL DEFAULT 1,
    INDEX product_stutus_ind (status_id),
    FOREIGN KEY (status_id)
        REFERENCES product_statuses (id),
    discount TINYINT DEFAULT 0,
    date_update DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

INSERT INTO pets(specie, breed, birth_date, price)
VALUES('DOG', 'Dog1', '2005-12-01', 12.20), ('CAT', 'Cat1', '2009-05-03', 200.00), ('DOG', 'Dog2', '2014-03-04', 13.30);

INSERT INTO pets(specie, breed, birth_date, price, discount)
VALUE('DOG', 'бульдог', '2009-12-01', 30.20 , 30);

INSERT INTO pets(specie, breed, birth_date, price, discount, status_id)
VALUE('DOG', 'КОРГИ', '2009-12-01', 600 , 20, 2);

INSERT INTO pets(specie, breed, birth_date, price, discount, status_id)
VALUE('DOG', 'ШПИЦ', '2009-12-01', 600 , 20, 1);

INSERT INTO pets(specie, breed, birth_date, price, discount, status_id)
VALUE('DOG', 'ШПИЦ', '2020-12-01', 250 , 10, 1);

CREATE TABLE IF NOT EXISTS feeds_and_other (
    id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY NOT NULL,
    type VARCHAR(150),
    brand VARCHAR(150),
    description VARCHAR(255),
    pet_type VARCHAR(250) NOT NULL DEFAULT 'all',
    fullText(pet_type),
    price DECIMAL UNSIGNED,
    statuses_id TINYINT UNSIGNED NOT NULL DEFAULT 1,
    INDEX status_ind (statuses_id),
    FOREIGN KEY (statuses_id)
        REFERENCES product_statuses (id),
    discount TINYINT DEFAULT 0,
    date_update DATETIME DEFAULT NOW()
)  ENGINE=INNODB;

INSERT INTO feeds_and_other(type, brand, description, price)
VALUE('feed', 'Farmina', 'feed_type: Wet, taste: chicken, packaging_type: bank, weight: 0.5', 6.30);

INSERT INTO feeds_and_other(type, brand, description, pet_type, price)
VALUES('feed', 'Farmina', 'feed_type: Wet, taste: chicken, packaging_type: bank, weight: 1.1', 'dog', 200.00), 
('feed', 'Farmina', 'feed_type: Wet, taste: beef, packaging_type: bank, weight: 0.5', 'cat', 11.20);

INSERT INTO feeds_and_other(type, brand, pet_type)
VALUES('feed',  'brend1', 'Cat, Dog, Parets' );

CREATE TABLE order_statuses (
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
    totalPaymentAmount DECIMAL UNSIGNED NOT NULL DEFAULT 0,
    totalDiscountAmount DECIMAL UNSIGNED NOT NULL DEFAULT 0,
    totalPaymentWithDiscountAmount DECIMAL UNSIGNED NOT NULL DEFAULT 0,
    date DATETIME DEFAULT NOW(),
    order_statuses_id TINYINT UNSIGNED NOT NULL DEFAULT 1,
    INDEX order_statuses_id (order_statuses_id),
    FOREIGN KEY (order_statuses_id)
        REFERENCES order_statuses (id)
)  ENGINE=INNODB;

create table product_types(
	id BIGINT UNSIGNED auto_increment primary key,
    name varchar(250)
)ENGINE=INNODB;

insert into product_types(name)
values ('PETS'), ('FEEDS_AND_OTHER');

CREATE TABLE order_products(
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

INSERT INTO orders(users_id)
values(3), (3);

INSERT INTO orders(users_id)
values(2), (2);

INSERT INTO orders(orders.users_id, orders.totalPaymentAmount, orders.totalDiscountAmount, orders.totalPaymentWithDiscountAmount, orders.order_statuses_id)
value(2, 100, 10, 90, 2);

INSERT INTO order_products(orders_id, product_types_id, pets_id, feeds_and_other_id)
values(1, 1, 1, null), (1, 2, null, 1), (2, 1, 2, null); 

CREATE TABLE IF NOT EXISTS verificate_сodes(
	users_id BIGINT UNSIGNED NOT NULL,
	INDEX users_id_ind (users_id),
	FOREIGN KEY (users_id)
		REFERENCES users (id),
	code VARCHAR(100) NOT NULL,
    status enum('OPEN', 'CLOSED')
) ENGINE=INNODB;