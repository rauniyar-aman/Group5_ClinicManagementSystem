/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package dao;

import model.AddDoctorModel;
import java.sql.*;
import Database.MySqlConnection;

/**
 *
 * @author amritchand
 */

public class AddDoctorDao {

    private final MySqlConnection db = new MySqlConnection();

    private final String INSERT_DOCTOR =
        "INSERT INTO doctors (doctor_name, email,pass, mobile, specialization) VALUES (?, ?, ?, ?, ?)";

    private final String CHECK_EMAIL =
        "SELECT 1 FROM doctors WHERE email = ? LIMIT 1";

    // Insert doctor
    public void addDoctor(AddDoctorModel doctor) throws SQLException {

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_DOCTOR)) {

            stmt.setString(1, doctor.getDoctorName());
            stmt.setString(2, doctor.getEmail());
            stmt.setString(3, doctor.getPassword()); 
            stmt.setString(4, doctor.getMobile());
            stmt.setString(5, doctor.getSpecialization());

            stmt.executeUpdate();
        }
    }

    // Check duplicate email
    public boolean isEmailExists(String email) throws SQLException {

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(CHECK_EMAIL)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }
}
