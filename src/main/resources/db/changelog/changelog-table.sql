--liquibase formatted sql

--changeset riadh:00000000000000-table-create-initial-tables
CREATE SEQUENCE sequence_generator START WITH 1000 INCREMENT BY 1;

CREATE TABLE product_categories (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    name VARCHAR(100) NOT NULL,
    barcode TEXT UNIQUE NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL
);

CREATE TABLE inventory_items (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    product_id BIGINT NOT NULL UNIQUE,
    units BIGINT NOT NULL
);
 
--changeset riadh:20251217214413-table-create-suppliers
CREATE TABLE suppliers (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    name VARCHAR(100) NOT NULL,
    address VARCHAR(200) NOT NULL,
    phone_number VARCHAR(15) NOT NULL,
    tin VARCHAR(20)
);

--changeset riadh:20251218113623-table-create-supplies
CREATE TABLE supplies (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    supplier_id BIGINT NOT NULL,
    invoice_number VARCHAR(50),
    supply_date TIMESTAMP WITH TIME ZONE NOT NULL,
    total DECIMAL NOT NULL
);

--changeset riadh:20251218115406-table-create-supply-items
CREATE TABLE supply_items (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    supply_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    units BIGINT NOT NULL,
    subtotal DECIMAL NOT NULL
);

--changeset riadh:20260113175033-table-create-users
CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    lang_key VARCHAR(10) NOT NULL,
    image_url VARCHAR(256),
    activated BOOLEAN NOT NULL,
    activation_key VARCHAR(20),
    reset_key VARCHAR(20),
    reset_date TIMESTAMP WITH TIME ZONE,
    created_at TIMESTAMP WITH TIME ZONE
);

--changeset riadh:20260113190155-table-create-authorities
CREATE TABLE authorities (
    name VARCHAR(50) PRIMARY KEY 
);

--changeset riadh:20260113190352-table-create-user-authorities
CREATE TABLE user_authorities (
    user_id BIGINT NOT NULL,
    authority_name VARCHAR(50) NOT NULL,
    PRIMARY KEY(user_id, authority_name)
);

--changeset riadh:20260113191304-table-create-stores
CREATE TABLE stores (
    id BIGINT PRIMARY KEY DEFAULT nextval('sequence_generator'),
    name VARCHAR(100) NOT NULL,
    tin VARCHAR(20),
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    owner_id BIGINT NOT NULL
);

--changeset riadh:20260113191324-table-create-store-employees
CREATE TABLE store_employees (
    user_id BIGINT NOT NULL,
    store_id BIGINT NOT NULL,
    PRIMARY KEY(user_id, store_id)
);
