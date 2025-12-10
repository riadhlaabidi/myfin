--liquibase formatted sql

--changeset riadh:00000000000002-data-initial-load context:dev
INSERT INTO product_categories(name) VALUES ('Dairy');
INSERT INTO product_categories(name) VALUES ('Pantry');

INSERT INTO products(name, image_url, category_id) VALUES ('Milk 1L', '/some-url-thing', 1);
INSERT INTO products(name, image_url, category_id) VALUES ('Yogurt', '/some-url-thing', 1);
INSERT INTO products(name, image_url, category_id) VALUES ('Pasta', '/some-url-thing', 2);
INSERT INTO products(name, image_url, category_id) VALUES ('Rice', '/some-url-thing', 2);


