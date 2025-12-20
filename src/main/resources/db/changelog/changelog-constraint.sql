--liquibase formatted sql

--changeset riadh:00000000000001-constraint-initial-constraints
ALTER TABLE products ADD FOREIGN KEY(category_id) REFERENCES product_categories(id);
ALTER TABLE inventory_items ADD FOREIGN KEY(product_id) REFERENCES products(id);

--changeset riadh:20251218115750-constraint-supplies-fk
ALTER TABLE supplies ADD FOREIGN KEY(supplier_id) REFERENCES suppliers(id);

--changeset riadh:20251218115933-constraint-supply-item-fk
ALTER TABLE supply_items ADD FOREIGN KEY(supply_id) REFERENCES supplies(id);
ALTER TABLE supply_items ADD FOREIGN KEY(product_id) REFERENCES products(id);
