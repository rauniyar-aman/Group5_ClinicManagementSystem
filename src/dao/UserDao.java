/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySQLConnection;
import Model.UserData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.lang.model.SourceVersion;


/**
 *
 * @author acer
 */
public class UserDao {
    
    public boolean insertUser(UserData user){
        boolean success = false;

        try{
            Connection con = MySQLConnection.getConnection();
            String sql = "INSERT INTO users(email, phone, name, password) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPassword());

            ps.executeUpdate();
            success = true;

        }catch(SQLException e){
            System.out.println("Insert Error: " + e.getMessage());
        }

        return success;
    }

    
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    public boolean registerUser(UserData user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

/**
 *
 * @author acer
 */

