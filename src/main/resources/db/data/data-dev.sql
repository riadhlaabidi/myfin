--liquibase formatted sql

--changeset riadh:00000000000002-data-initial-load context:dev
INSERT INTO product_categories(name) VALUES ("Mobile Recharge");
INSERT INTO product_categories(name) VALUES ("Tobacco");

INSERT INTO products(name, image_url, category_id) VALUES ("Recharge Ticket 5DT Brand 1", "/some-url-thing", 1);
INSERT INTO products(name, image_url, category_id) VALUES ("Recharge Ticket 5DT Brand 2", "/some-url-thing", 1);
INSERT INTO products(name, image_url, category_id) VALUES ("Recharge Ticket 5DT Brand 3", "/some-url-thing", 1);
INSERT INTO products(name, image_url, category_id) VALUES ("Recharge Ticket 1DT Brand 1", "/some-url-thing", 1);
INSERT INTO products(name, image_url, category_id) VALUES ("Cigarettes Brand 1", "/some-url-thing", 1);
INSERT INTO products(name, image_url, category_id) VALUES ("Cigarettes Brand 2", "/some-url-thing", 1);


