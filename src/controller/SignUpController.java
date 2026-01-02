/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.SignUpDao;
import model.SignUpModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;

/**
 *
 * @author amritchand
 */
public class SignUpController {
    
    private final SignUpDao userDao;

    public SignUpController() {
        userDao = new SignUpDao();
    }

    public boolean registerUser(String name, String email, String phone, String password, String confirmPassword) {

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(null, "Please enter a valid email!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            if(userDao.isEmailExists(email)) {
                JOptionPane.showMessageDialog(null, "Email already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            SignUpModel user = new SignUpModel();
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password); // Optional: hash here

            userDao.addUser(user);
            JOptionPane.showMessageDialog(null, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}

