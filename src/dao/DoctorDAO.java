/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Database.MySqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Doctor;

/**
 * DoctorDAO - Data Access Object for Doctor entity
 * Handles all database operations related to doctors
 * 
 * @author Group 5
 */
public class DoctorDAO {

    private final MySqlConnection db = new MySqlConnection();

    private final String SELECT_ALL_DOCTORS = "SELECT doctor_id, doctor_name, email, mobile, specialization FROM doctors ORDER BY doctor_id";

    /**
     * Retrieve all doctors from the database
     * 
     * @return List of Doctor objects
     */
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();

        try (Connection conn = db.openconnection();
                PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_DOCTORS);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(rs.getInt("doctor_id"));
                doctor.setDoctorName(rs.getString("doctor_name"));
                doctor.setEmail(rs.getString("email"));
                doctor.setMobile(rs.getString("mobile"));
                doctor.setSpecialization(rs.getString("specialization"));

                doctors.add(doctor);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching doctors: " + e.getMessage());
            e.printStackTrace();
        }

        return doctors;
    }
}
