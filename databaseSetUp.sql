-- ========================================
-- Clinic Management System Database Setup
-- ========================================
-- Author: Group 5
-- Date: December 31, 2025
-- Description: Complete database schema for clinic management system
-- ========================================

-- Create database
CREATE DATABASE IF NOT EXISTS clinicdb;
USE clinicdb;

-- ========================================
-- TABLE: admin
-- Purpose: Store administrator credentials
-- ========================================
CREATE TABLE IF NOT EXISTS admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    admin_name VARCHAR(100) NOT NULL,
    admin_email VARCHAR(100) UNIQUE NOT NULL,
    pass1 VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_admin_email (admin_email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert default admin (will be handled by application too)
INSERT INTO admin (admin_name, admin_email, pass1) 
VALUES ('Aman Rauniyar', 'amangupta00121212@gmail.com', 'aman@123')
ON DUPLICATE KEY UPDATE admin_name = admin_name;

-- ========================================
-- TABLE: doctors
-- Purpose: Store doctor information
-- ========================================
CREATE TABLE IF NOT EXISTS doctors (
    doctor_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    pass VARCHAR(255) NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    status ENUM('active', 'inactive') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_doctor_email (email),
    INDEX idx_doctor_specialization (specialization)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Sample doctors data
INSERT INTO doctors (doctor_name, email, pass, mobile, specialization) VALUES
('Dr. Sarah Johnson', 'sarah.johnson@clinic.com', 'doctor123', '9841234567', 'Cardiology'),
('Dr. Michael Chen', 'michael.chen@clinic.com', 'doctor123', '9841234568', 'Pediatrics'),
('Dr. Emily Brown', 'emily.brown@clinic.com', 'doctor123', '9841234569', 'Dermatology'),
('Dr. James Wilson', 'james.wilson@clinic.com', 'doctor123', '9841234570', 'Orthopedics'),
('Dr. Lisa Anderson', 'lisa.anderson@clinic.com', 'doctor123', '9841234571', 'General Medicine')
ON DUPLICATE KEY UPDATE doctor_name = doctor_name;

-- ========================================
-- TABLE: patients (also referred as 'users' in some parts)
-- Purpose: Store patient/user information
-- ========================================
CREATE TABLE IF NOT EXISTS patients (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(255) NOT NULL,
    address TEXT,
    date_of_birth DATE,
    gender ENUM('Male', 'Female', 'Other'),
    blood_group VARCHAR(5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_patient_email (email),
    INDEX idx_patient_phone (phone)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Alias table for backward compatibility (if 'users' table is referenced)
CREATE OR REPLACE VIEW users AS
SELECT 
    id,
    name,
    email,
    phone,
    password,
    created_at,
    updated_at
FROM patients;

-- Sample patients data
INSERT INTO patients (name, email, phone, password, gender) VALUES
('John Smith', 'john.smith@email.com', '9876543210', 'patient123', 'Male'),
('Mary Johnson', 'mary.johnson@email.com', '9876543211', 'patient123', 'Female'),
('Robert Williams', 'robert.w@email.com', '9876543212', 'patient123', 'Male'),
('Jennifer Davis', 'jennifer.d@email.com', '9876543213', 'patient123', 'Female')
ON DUPLICATE KEY UPDATE name = name;

-- ========================================
-- TABLE: appointments
-- Purpose: Store patient appointment bookings
-- ========================================
CREATE TABLE IF NOT EXISTS appointments (
    appointment_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    status ENUM('Scheduled', 'Completed', 'Cancelled', 'Rescheduled') DEFAULT 'Scheduled',
    symptoms TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE,
    INDEX idx_appointment_date (appointment_date),
    INDEX idx_appointment_status (status),
    INDEX idx_patient_appointments (patient_id),
    INDEX idx_doctor_appointments (doctor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- TABLE: prescriptions
-- Purpose: Store medical prescriptions
-- ========================================
CREATE TABLE IF NOT EXISTS prescriptions (
    prescription_id INT PRIMARY KEY AUTO_INCREMENT,
    appointment_id INT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    medicines TEXT NOT NULL,
    dosage TEXT,
    instructions TEXT,
    diagnosis TEXT,
    prescription_date DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE SET NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE,
    INDEX idx_prescription_date (prescription_date),
    INDEX idx_patient_prescriptions (patient_id),
    INDEX idx_doctor_prescriptions (doctor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- TABLE: doctor_availability
-- Purpose: Store doctor availability schedules
-- ========================================
CREATE TABLE IF NOT EXISTS doctor_availability (
    availability_id INT PRIMARY KEY AUTO_INCREMENT,
    doctor_id INT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    available_date DATE NOT NULL,
    available_time TIME NOT NULL,
    is_booked BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE,
    FOREIGN KEY (email) REFERENCES doctors(email) ON DELETE CASCADE,
    INDEX idx_availability_date (available_date),
    INDEX idx_doctor_availability (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- TABLE: medical_records
-- Purpose: Store patient medical history
-- ========================================
CREATE TABLE IF NOT EXISTS medical_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    record_date DATE NOT NULL,
    diagnosis TEXT,
    treatment TEXT,
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE,
    INDEX idx_patient_records (patient_id),
    INDEX idx_record_date (record_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ========================================
-- USEFUL VIEWS
-- ========================================

-- View: Doctor Appointments with full details
CREATE OR REPLACE VIEW doctor_appointments_view AS
SELECT 
    a.appointment_id,
    a.appointment_date,
    a.appointment_time,
    a.status,
    a.symptoms,
    d.doctor_name,
    d.email AS doctor_email,
    d.specialization,
    p.name AS patient_name,
    p.email AS patient_email,
    p.phone AS patient_phone
FROM appointments a
JOIN doctors d ON a.doctor_id = d.doctor_id
JOIN patients p ON a.patient_id = p.id
ORDER BY a.appointment_date DESC, a.appointment_time DESC;

-- View: Patient Prescriptions with full details
CREATE OR REPLACE VIEW patient_prescriptions_view AS
SELECT 
    pr.prescription_id,
    pr.prescription_date,
    pr.medicines,
    pr.dosage,
    pr.instructions,
    pr.diagnosis,
    p.name AS patient_name,
    p.email AS patient_email,
    d.doctor_name,
    d.email AS doctor_email,
    d.specialization
FROM prescriptions pr
JOIN patients p ON pr.patient_id = p.id
JOIN doctors d ON pr.doctor_id = d.doctor_id
ORDER BY pr.prescription_date DESC;

-- View: Available doctor slots
CREATE OR REPLACE VIEW available_slots_view AS
SELECT 
    da.availability_id,
    da.available_date,
    da.available_time,
    d.doctor_name,
    d.email,
    d.specialization,
    d.mobile
FROM doctor_availability da
JOIN doctors d ON da.email = d.email
WHERE da.is_booked = FALSE
    AND da.available_date >= CURDATE()
ORDER BY da.available_date, da.available_time;

-- ========================================
-- STORED PROCEDURES
-- ========================================

-- Procedure: Book an appointment
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS BookAppointment(
    IN p_patient_id INT,
    IN p_doctor_id INT,
    IN p_appointment_date DATE,
    IN p_appointment_time TIME,
    IN p_symptoms TEXT
)
BEGIN
    DECLARE v_patient_name VARCHAR(100);
    DECLARE v_doctor_name VARCHAR(100);
    DECLARE v_department VARCHAR(100);
    
    -- Get patient name
    SELECT name INTO v_patient_name FROM patients WHERE id = p_patient_id;
    
    -- Get doctor details
    SELECT doctor_name, specialization INTO v_doctor_name, v_department 
    FROM doctors WHERE doctor_id = p_doctor_id;
    
    -- Insert appointment
    INSERT INTO appointments (
        patient_id, doctor_id, patient_name, doctor_name, 
        department, appointment_date, appointment_time, symptoms
    ) VALUES (
        p_patient_id, p_doctor_id, v_patient_name, v_doctor_name,
        v_department, p_appointment_date, p_appointment_time, p_symptoms
    );
    
    -- Mark the slot as booked if exists
    UPDATE doctor_availability 
    SET is_booked = TRUE 
    WHERE doctor_id = p_doctor_id 
        AND available_date = p_appointment_date 
        AND available_time = p_appointment_time;
        
    SELECT 'Appointment booked successfully' AS message;
END //
DELIMITER ;

-- Procedure: Add prescription
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS AddPrescription(
    IN p_appointment_id INT,
    IN p_patient_id INT,
    IN p_doctor_id INT,
    IN p_medicines TEXT,
    IN p_dosage TEXT,
    IN p_instructions TEXT,
    IN p_diagnosis TEXT
)
BEGIN
    DECLARE v_patient_name VARCHAR(100);
    DECLARE v_doctor_name VARCHAR(100);
    
    -- Get names
    SELECT name INTO v_patient_name FROM patients WHERE id = p_patient_id;
    SELECT doctor_name INTO v_doctor_name FROM doctors WHERE doctor_id = p_doctor_id;
    
    -- Insert prescription
    INSERT INTO prescriptions (
        appointment_id, patient_id, doctor_id, patient_name, doctor_name,
        medicines, dosage, instructions, diagnosis, prescription_date
    ) VALUES (
        p_appointment_id, p_patient_id, p_doctor_id, v_patient_name, v_doctor_name,
        p_medicines, p_dosage, p_instructions, p_diagnosis, CURDATE()
    );
    
    -- Update appointment status
    UPDATE appointments SET status = 'Completed' WHERE appointment_id = p_appointment_id;
    
    SELECT 'Prescription added successfully' AS message;
END //
DELIMITER ;

-- ========================================
-- SAMPLE DATA FOR TESTING
-- ========================================

-- Sample appointments
INSERT INTO appointments (patient_id, doctor_id, patient_name, doctor_name, department, appointment_date, appointment_time, status) VALUES
(1, 1, 'John Smith', 'Dr. Sarah Johnson', 'Cardiology', '2025-01-15', '10:00:00', 'Scheduled'),
(2, 2, 'Mary Johnson', 'Dr. Michael Chen', 'Pediatrics', '2025-01-15', '11:00:00', 'Scheduled'),
(3, 3, 'Robert Williams', 'Dr. Emily Brown', 'Dermatology', '2025-01-16', '09:00:00', 'Scheduled')
ON DUPLICATE KEY UPDATE status = status;

-- Sample doctor availability
INSERT INTO doctor_availability (doctor_id, name, email, available_date, available_time) VALUES
(1, 'Dr. Sarah Johnson', 'sarah.johnson@clinic.com', '2025-01-15', '10:00:00'),
(1, 'Dr. Sarah Johnson', 'sarah.johnson@clinic.com', '2025-01-15', '14:00:00'),
(2, 'Dr. Michael Chen', 'michael.chen@clinic.com', '2025-01-15', '11:00:00'),
(2, 'Dr. Michael Chen', 'michael.chen@clinic.com', '2025-01-16', '10:00:00'),
(3, 'Dr. Emily Brown', 'emily.brown@clinic.com', '2025-01-16', '09:00:00')
ON DUPLICATE KEY UPDATE available_date = available_date;

-- ========================================
-- UTILITY QUERIES
-- ========================================

-- Get all appointments for a specific patient
-- SELECT * FROM appointments WHERE patient_id = 1 ORDER BY appointment_date DESC;

-- Get all prescriptions for a specific patient
-- SELECT * FROM prescriptions WHERE patient_id = 1 ORDER BY prescription_date DESC;

-- Get doctor schedule for a specific date
-- SELECT * FROM doctor_availability WHERE email = 'sarah.johnson@clinic.com' AND available_date = '2025-01-15';

-- Get all available slots
-- SELECT * FROM available_slots_view;

-- Count appointments by status
-- SELECT status, COUNT(*) as count FROM appointments GROUP BY status;

-- Get today's appointments
-- SELECT * FROM doctor_appointments_view WHERE appointment_date = CURDATE();

-- ========================================
-- END OF DATABASE SETUP
-- ========================================

-- Display success message
SELECT 'Database setup completed successfully!' AS Status;
SELECT 'Tables created: admin, doctors, patients, appointments, prescriptions, doctor_availability, medical_records' AS Info;
SELECT 'Sample data inserted for testing' AS Note;


CREATE TABLE notice (
    notice_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    notice_date DATE NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);