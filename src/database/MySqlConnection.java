/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author acer
 */
public class MySqlConnection implements Database {
    
    
    


    @Override
    public void closeConnection(Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResultSet runQuery(Connection conn, String Query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int executeUpdate(Connection conn, String Query) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Connection openconnection() {
 try{
            String password = "12345";
            String username = "root";
            String database = "Clinicdb";
            Connection connection;
            connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/" +database, username, password);
            if (connection == null) {
                System.out.println("Database connection fail");
            } else {
                System.out.println("Database connection successfull");
            }
            System.out.println(database);
            return connection;
        }catch(SQLException e) {
           System.out.println(e);
           return null;
                
                
                
                
        }     }

    
}
    
   
   
