--liquibase formatted sql

--changeset Rohit:create-user-table

CREATE TABLE patients (
                       id CHAR(36) PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       phone VARCHAR(20),
                       role VARCHAR(50) NOT NULL,
                       is_active BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP ,
                       updated_at TIMESTAMP
);