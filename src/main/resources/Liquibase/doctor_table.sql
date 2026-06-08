--liquibase formatted sql
--changeset Rohit:create-doctor-table

CREATE TABLE doctor (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    specialization VARCHAR(255),
    licence_number VARCHAR(100),
    is_active BOOLEAN DEFAULT TRUE,
    avaliable_days DOUBLE,
    work_start_time TIME,
    work_end_time TIME,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    PRIMARY KEY (id),
    UNIQUE KEY uk_doctor_email (email),
    UNIQUE KEY uk_doctor_licenseNumber (licence_number)
);