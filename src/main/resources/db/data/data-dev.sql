--liquibase formatted sql

--changeset riadh:00000000000002-data-initial-load context:dev
INSERT INTO product_categories(id, name) VALUES (1, 'Dairy');
INSERT INTO product_categories(id, name) VALUES (2, 'Pantry');

INSERT INTO products(id, name, barcode, image_url, category_id) VALUES (1, 'Milk 1L', '11111111', '/some-url-thing', 1);
INSERT INTO products(id, name, barcode, image_url, category_id) VALUES (2, 'Yogurt', '22222222', '/some-url-thing', 1);
INSERT INTO products(id, name, barcode, image_url, category_id) VALUES (3, 'Pasta', '33333333', '/some-url-thing', 2);
INSERT INTO products(id, name, barcode, image_url, category_id) VALUES (4, 'Rice', '44444444', '/some-url-thing', 2);


