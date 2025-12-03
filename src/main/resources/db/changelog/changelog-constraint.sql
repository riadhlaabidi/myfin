--liquibase formatted sql

--changeset riadh:00000000000001-constraint-initial-constraints
ALTER TABLE products ADD FOREIGN KEY(category_id) REFERENCES product_categories(id);
ALTER TABLE inventory ADD FOREIGN KEY(product_id) REFERENCES products(id);
