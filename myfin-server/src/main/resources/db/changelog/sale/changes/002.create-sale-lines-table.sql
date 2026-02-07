--liquibase formatted sql

--changeset riadh:20260207114930-create-sale-lines-table
CREATE TABLE sale_lines (
    id UUID PRIMARY KEY,
    sale_id UUID NOT NULL,
    product_id UUID NOT NULL,
    quantity DECIMAL NOT NULL,
    unit VARCHAR(20) NOT NULL
);

ALTER TABLE sale_lines ADD FOREIGN KEY(sale_id) REFERENCES sales(id);
