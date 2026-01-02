/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
import Database.MySqlConnection;

/**
 *
 * @author amang
 */
public class FetchDataDao{
    
    private final MySqlConnection db = new MySqlConnection();
    

    public String[] fetchByEmail(String email_id) {
        String[] details = new String[3]; // fullName, department, email

        String query = "SELECT doctor_name, specialization FROM doctors WHERE email = ?";

        try (Connection con = db.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, email_id);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null; // no doctor found
            }

            details[0] = rs.getString("doctor_name");
            details[1] = rs.getString("specialization");


            return details;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // doctor not found or error
    }
    
}
