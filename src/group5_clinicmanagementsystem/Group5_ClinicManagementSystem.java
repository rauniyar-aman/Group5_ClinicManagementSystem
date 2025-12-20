/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Group5_ClinicManagementSystem;

import controller.UserController;
import database1.Database;
import database1.MySqlConnection;
import view.SignUp;
import java.sql.*;
/**
 *
 * @author oakin
 */
public class Group5_ClinicManagementSystem {

    /**Mellow
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Database db = new MySqlConnection();
        if(db.openconnection() !=null){
            System.out.println("connection opened");
        }else{
            System.out.println("connection closed");
        SignUp signup = new SignUp();
    UserController usercontroller = new UserController(signup);
    usercontroller.open();

        }
    }
}
