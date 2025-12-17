/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import view.SignUp;
import controller.UserController;
import dao.UserDao;
/**
 *
 * @author acer
 */
public class Main {
     public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {

            // 1. Create View
            SignUp signUpView = new SignUp();

            // 2. Create Model (DAO)
            UserDao userDao = new UserDao();

            // 3. Create Controller
            UserController controller = new UserController(signUpView, userDao);

            // 4. Show View
            signUpView.setLocationRelativeTo(null);
            signUpView.setVisible(true);
        });
    }
}


