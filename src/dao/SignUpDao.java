/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.SignUpModel;
import java.sql.*;
import Database.MySqlConnection; 

/**
 *
 * @author amritchand
 */
public class SignUpDao {


    private final MySqlConnection db = new MySqlConnection();

    private final String INSERT_USER = "INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)";
    private final String CHECK_EMAIL = "SELECT 1 FROM users WHERE email = ? LIMIT 1";

    public void addUser(SignUpModel user) throws SQLException {
        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPhone());
            stmt.setString(4, user.getPassword()); // Optional: hash password

            stmt.executeUpdate();
        }
    }

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
