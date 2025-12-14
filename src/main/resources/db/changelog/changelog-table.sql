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

