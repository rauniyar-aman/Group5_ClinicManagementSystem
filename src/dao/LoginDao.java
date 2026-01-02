/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author amang
 */

public class LoginDao {
    
    public String current_doctor;
    public String patient_email;

    // Default admin details
    private final String DEFAULT_ADMIN_NAME = "Aman Rauniyar";
    private final String DEFAULT_ADMIN_EMAIL = "amangupta00121212@gmail.com";
    private final String DEFAULT_ADMIN_PASS = "aman@123";

    private MySqlConnection db = new MySqlConnection();

    // Constructor → insert default admin if not exists
    public LoginDao() {
        try (Connection conn = db.openconnection()) {

            String checkQuery = "SELECT 1 FROM admin WHERE admin_email=?";
            PreparedStatement psCheck = conn.prepareStatement(checkQuery);
            psCheck.setString(1, DEFAULT_ADMIN_EMAIL);
            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {
                String insert = "INSERT INTO admin (admin_name, admin_email, pass1) VALUES (?, ?, ?)";
                PreparedStatement psInsert = conn.prepareStatement(insert);
                psInsert.setString(1, DEFAULT_ADMIN_NAME);
                psInsert.setString(2, DEFAULT_ADMIN_EMAIL);
                psInsert.setString(3, DEFAULT_ADMIN_PASS); 
                psInsert.executeUpdate();

                System.out.println("Default admin inserted.");
            } else {
                System.out.println("Default admin already exists.");
            }

        } catch (Exception e) {
            System.err.println("Admin insert error: " + e.getMessage());
        }
    }

    // LOGIN CHECK
    public String login(String email2, String pass2) {

        try (Connection conn = db.openconnection()) {

      
            String adminSql = "SELECT 1 FROM admin WHERE admin_email=? AND pass1=?";
            PreparedStatement psAdmin = conn.prepareStatement(adminSql);
            psAdmin.setString(1, email2);
            psAdmin.setString(2, pass2);
            ResultSet rsAdmin = psAdmin.executeQuery();

            if (rsAdmin.next()) {
                return "admin";
            }

            String doctorSql = "SELECT 1 FROM doctors WHERE email=? AND pass=?";
            PreparedStatement psDoctor = conn.prepareStatement(doctorSql);
            psDoctor.setString(1, email2);
            psDoctor.setString(2, pass2);
            ResultSet rsDoctor = psDoctor.executeQuery();

            if (rsDoctor.next()) {
                current_doctor = email2;
                
                
                return "doctor";
            }

            String userSql = "SELECT 1 FROM users WHERE email=? AND password=?";
            PreparedStatement psUser = conn.prepareStatement(userSql);
            psUser.setString(1, email2);
            psUser.setString(2, pass2);
            ResultSet rsUser = psUser.executeQuery();

            if (rsUser.next()) {
                patient_email = email2;
                return "user";
            }

        } catch (Exception e) {
            System.err.println("Login error: " + e.getMessage());
        }
       
        return null; // invalid credentials
    }
    
    public String getCurrentDoctor() {
        return current_doctor;
    }
    
    public String getPatientEmail() {
        return patient_email;
    }
    

    public String getDoctorNameByEmail(String current_doctor) {
        String doctorName = null;

        String sql = "SELECT doctor_name FROM doctors WHERE email = ?";

        try (Connection conn = db.openconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, current_doctor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doctorName = rs.getString("doctor_name");
            }

        } catch (Exception e) {
            System.err.println("Error fetching doctor name: " + e.getMessage());
        }

        return doctorName;
    }
    
    
    public String getDoctorDepartmentByEmail(String current_doctor) {
        String doctorSpecialization = null;

        String sql = "SELECT specialization FROM doctors WHERE email = ?";

        try (Connection conn = db.openconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, current_doctor);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                doctorSpecialization = rs.getString("specialization");
            }

        } catch (Exception e) {
            System.err.println("Error fetching doctor specialization: " + e.getMessage());
        }

        return doctorSpecialization;
    }
    
    public int getPatientIdByEmail(String email) {
        int patientId = -1;
        String sql = "SELECT id FROM users WHERE email = ?";
        try (Connection conn = db.openconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                patientId = rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println("Error fetching patient id: " + e.getMessage());
        }
        return patientId;
    }
    
    public String getPatientNameByEmail(String email) {
        String name = null;
        String sql = "SELECT name FROM users WHERE email = ?";
        try (Connection conn = db.openconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (Exception e) {
            System.err.println("Error fetching patient name: " + e.getMessage());
        }
        return name;
    }
       
}
