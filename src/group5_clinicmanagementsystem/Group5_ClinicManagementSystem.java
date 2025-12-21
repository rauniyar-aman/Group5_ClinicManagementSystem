/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package group5_clinicmanagementsystem;

import controller.UserController;
import dao.UserDao;
import database.Database;
import database.MySqlConnection;
import View.SignUp;
/**
 *
 * @author oakin
 */
public class Group5_ClinicManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Check DB connection
        Database db = new MySqlConnection();
        if (db.openconnection() != null) {
            System.out.println("Database connection opened");
        } else {
            System.out.println("Database connection failed");
        }

        // Create UI and wire controller
        SignUp signup = new SignUp();
        UserDao userDao = new UserDao();
        UserController usercontroller = new UserController(signup, userDao);

        signup.setLocationRelativeTo(null);
        signup.setVisible(true);
    }
}
