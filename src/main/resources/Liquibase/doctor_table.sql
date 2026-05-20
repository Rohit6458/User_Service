/*--liquibase formatted sql
--changeset Rohit:create-doctor-table

CREATE TABLE doctors (

                         id CHAR(36) primary key not null,
                         user_id CHAR(36),
                         specialization VARCHAR(255),
                         license_number VARCHAR(255) UNIQUE,
                         slot_duration_mins INT,
                         available_days VARCHAR(100),
                         work_start_time TIME,
                         work_end_time TIME,
                         CONSTRAINT fk_doctor_user
                             FOREIGN KEY (user_id)
                                 REFERENCES patients(id)
);*/