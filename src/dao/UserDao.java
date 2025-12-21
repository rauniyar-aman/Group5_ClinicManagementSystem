/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;


/**
 *
 * @author acer
 */
public class UserDao {
    MySqlConnection mysql = new MySqlConnection();

    public boolean register(User user) {
        
        Connection conn = mysql.openconnection();
       
       
        if (conn == null) {
            System.out.println("UserDao: Failed to open DB connection");
            return false;
        }
            String sql = "INSERT INTO users(name, email, phone, password) VALUES (?, ?, ?, ?)";
            try
            (PreparedStatement pstm = conn.prepareStatement(sql)){            
            pstm.setString(1, user.getName());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPhone());
            pstm.setString(4, user.getPassword());
            pstm.executeUpdate(); 
            return true; 
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    
    public boolean checkUser(User user) {

        Connection conn = mysql.openconnection();
       
        if (conn == null) {
            System.out.println("DB connection failed");
            return false;
        }

        String sql = "SELECT * FROM users WHERE phone=? OR email=?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, user.getPhone());
            pstm.setString(2, user.getEmail());

            ResultSet rs = pstm.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            
            e.printStackTrace();
        
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    // Convenience overloads to support UserData1 usages from older UI code
    public boolean checkUser(model.UserData1 userdata) {
        User tmp = new User(userdata.getName(), userdata.getEmail(), userdata.getPhone(), userdata.getPassword());
        return checkUser(tmp);
    }

    public boolean SignUp(model.UserData1 userdata) {
        User tmp = new User(userdata.getName(), userdata.getEmail(), userdata.getPhone(), userdata.getPassword());
        return register(tmp);
    }
}
                
      