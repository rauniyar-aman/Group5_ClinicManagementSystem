/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author amang
 */

/**
 * DAO class to delete doctor
 */
public class DeleteDoctorDao {

    private final MySqlConnection db = new MySqlConnection();


    public boolean deleteByEmail(String email) {
        String query = "DELETE FROM doctors WHERE email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, email);  // use setString for email
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

