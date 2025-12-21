/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Appointment;
/**
 *
 * @author acer
 */
public class AppointmentDAO {
    private final MySqlConnection mysql = new MySqlConnection();

    public boolean saveAppointment(Appointment appt) {
        Connection con = mysql.openconnection();
        if (con == null) {
            System.err.println("AppointmentDAO: DB connection failed");
            return false;
        }
        String sql = "INSERT INTO appointments(department, doctor, date, time) VALUES (?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, appt.getDepartment());
            ps.setString(2, appt.getDoctor());
            ps.setString(3, appt.getDate());
            ps.setString(4, appt.getTime());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { con.close(); } catch (SQLException ignore) {}
        }
    }
}
