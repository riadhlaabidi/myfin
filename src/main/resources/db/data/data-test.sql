--liquibase formatted sql

--changeset riadh:00000000000003-data-initial-load context:test
INSERT INTO product_categories(id, name) VALUES (1, 'Dairy');
INSERT INTO product_categories(id, name) VALUES (2, 'Pantry');

--changeset riadh:20251213204444-data-add-products context:test
INSERT INTO products(id, name, barcode, image_url, category_id) VALUES (1, 'Milk 1L', '11223344', '/some-url-thing', 1);
INSERT INTO products(id ,name, barcode, image_url, category_id) VALUES (2, 'Pasta', '22334455', '/some-url-thing', 2);
