--liquibase formatted sql

--changeset riadh:00000000000003-data-initial-load context:test
INSERT INTO product_categories(name) VALUES ('Dairy');
INSERT INTO product_categories(name) VALUES ('Pantry');
