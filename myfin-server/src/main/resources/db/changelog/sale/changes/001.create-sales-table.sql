--liquibase formatted sql

--changeset riadh:20260207010202-create-sales-table
CREATE TABLE sales (
    id UUID PRIMARY KEY,
    status VARCHAR(20) NOT NULL,
    store_id UUID NOT NULL,
    terminal_id UUID NOT NULL,
    operator_id UUID NOT NULL,
    started_at TIMESTAMP WITH TIME ZONE NOT NULL,
    finished_at TIMESTAMP WITH TIME ZONE
);


