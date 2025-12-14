--liquibase formatted sql

--changeset riadh:00000000000003-data-initial-load context:test
INSERT INTO product_categories(name) VALUES ('Dairy');
INSERT INTO product_categories(name) VALUES ('Pantry');

--changeset riadh:20251213204444-data-add-products context:test
INSERT INTO products(name, barcode, image_url, category_id) VALUES ('Milk 1L', '11223344', '/some-url-thing', 1);
INSERT INTO products(name, barcode, image_url, category_id) VALUES ('Pasta', '22334455', '/some-url-thing', 2);
