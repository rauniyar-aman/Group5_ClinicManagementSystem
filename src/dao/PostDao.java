/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import model.DoctorAvailability;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

/**
 *
 * @author amritchand
 */
public class PostDao {


    private MySqlConnection db = new MySqlConnection();

    public boolean saveAvailability(DoctorAvailability availability) {
        String sql = "INSERT INTO doctor_availability (name, email, available_date, available_time,specialization) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = db.openconnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, availability.getName());
            ps.setString(2, availability.getEmail());
            ps.setDate(3, (Date) availability.getAvailableDate());
            ps.setTime(4, availability.getAvailableTime());
            ps.setString(5, availability.getSpecialization());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.err.println("Error saving availability: " + e.getMessage());
            return false;
        }
    }
}
