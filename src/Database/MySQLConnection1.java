/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author acer
 */
public class MySQLConnection1 {
     private static final String URL = "jdbc:mysql://localhost:3306/clinicdb";
    private static final String USER = "root";
    private static final String PASS = "1212";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
            return null;
        }
    } 
}
