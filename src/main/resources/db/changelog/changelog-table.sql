--liquibase formatted sql

--changeset riadh:00000000000000-table-create-initial-tables
CREATE TABLE product_categories (
    id BIGINT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    category_id BIGINT NOT NULL
);

CREATE TABLE inventory (
    id BIGINT PRIMARY KEY,
    product_id BIGINT NOT NULL UNIQUE,
    units BIGINT NOT NULL
);

