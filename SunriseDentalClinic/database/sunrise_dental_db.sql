CREATE DATABASE IF NOT EXISTS sunrise_dental_db;

USE sunrise_dental_db;

CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS dentists (
    dentist_id INT AUTO_INCREMENT PRIMARY KEY,
    dentist_name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS treatments (
    treatment_id INT AUTO_INCREMENT PRIMARY KEY,
    treatment_name VARCHAR(100) NOT NULL,
    treatment_cost DECIMAL(10,2) NOT NULL,
    consultation_fee DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_number VARCHAR(30) UNIQUE,
    patient_id INT NOT NULL,
    dentist_id INT NOT NULL,
    treatment_id INT NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',

    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
        REFERENCES patients(patient_id),

    CONSTRAINT fk_appointment_dentist
        FOREIGN KEY (dentist_id)
        REFERENCES dentists(dentist_id),

    CONSTRAINT fk_appointment_treatment
        FOREIGN KEY (treatment_id)
        REFERENCES treatments(treatment_id)
);

CREATE TABLE IF NOT EXISTS bills (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT NOT NULL,
    treatment_cost DECIMAL(10,2) NOT NULL,
    consultation_fee DECIMAL(10,2) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_bill_appointment
        FOREIGN KEY (appointment_id)
        REFERENCES appointments(appointment_id),

    CONSTRAINT unique_appointment_bill
        UNIQUE (appointment_id)
);

INSERT INTO dentists (dentist_name, specialization)
SELECT 'Dr. Nimal Perera', 'General Dentistry'
WHERE NOT EXISTS (
    SELECT 1 FROM dentists
    WHERE dentist_name = 'Dr. Nimal Perera'
);

INSERT INTO dentists (dentist_name, specialization)
SELECT 'Dr. Sara Fernando', 'Orthodontics'
WHERE NOT EXISTS (
    SELECT 1 FROM dentists
    WHERE dentist_name = 'Dr. Sara Fernando'
);

INSERT INTO dentists (dentist_name, specialization)
SELECT 'Dr. Ahmed Rizwan', 'Oral Surgery'
WHERE NOT EXISTS (
    SELECT 1 FROM dentists
    WHERE dentist_name = 'Dr. Ahmed Rizwan'
);

INSERT INTO treatments
(treatment_name, treatment_cost, consultation_fee)
SELECT 'Dental Cleaning', 3000.00, 1500.00
WHERE NOT EXISTS (
    SELECT 1 FROM treatments
    WHERE treatment_name = 'Dental Cleaning'
);

INSERT INTO treatments
(treatment_name, treatment_cost, consultation_fee)
SELECT 'Tooth Filling', 5000.00, 1500.00
WHERE NOT EXISTS (
    SELECT 1 FROM treatments
    WHERE treatment_name = 'Tooth Filling'
);

INSERT INTO treatments
(treatment_name, treatment_cost, consultation_fee)
SELECT 'Tooth Extraction', 7000.00, 2000.00
WHERE NOT EXISTS (
    SELECT 1 FROM treatments
    WHERE treatment_name = 'Tooth Extraction'
);

INSERT INTO treatments
(treatment_name, treatment_cost, consultation_fee)
SELECT 'Root Canal', 15000.00, 2500.00
WHERE NOT EXISTS (
    SELECT 1 FROM treatments
    WHERE treatment_name = 'Root Canal'
);

INSERT INTO treatments
(treatment_name, treatment_cost, consultation_fee)
SELECT 'Orthodontic Consultation', 4000.00, 2000.00
WHERE NOT EXISTS (
    SELECT 1 FROM treatments
    WHERE treatment_name = 'Orthodontic Consultation'
);