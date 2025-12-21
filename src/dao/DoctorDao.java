package dao;

import database.MySqlConnection;
import model.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoctorDao {
    private final MySqlConnection mysql = new MySqlConnection();

    public boolean addDoctor(Doctor d) {
        Connection conn = mysql.openconnection();
        if (conn == null) return false;
        String sql = "INSERT INTO doctors(name,email,phone,department) VALUES (?,?,?,?)";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, d.getName());
            pstm.setString(2, d.getEmail());
            pstm.setString(3, d.getPhone());
            pstm.setString(4, d.getDepartment());
            pstm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { conn.close(); } catch (SQLException ignore) {}
        }
    }

    public boolean deleteDoctorByEmail(String email) {
        Connection conn = mysql.openconnection();
        if (conn == null) return false;
        String sql = "DELETE FROM doctors WHERE email = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, email);
            int rows = pstm.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { conn.close(); } catch (SQLException ignore) {}
        }
    }
}