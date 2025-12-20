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
