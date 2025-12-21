/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Dao.UserDao;
import model.User;
import view.SignUp;

/**
 * Simple UserController providing registration service used by views or tests.
 */
public class UserController {
    private final UserDao dao;
    private SignUp view;

    public UserController() {
        this.dao = new UserDao();
    }

    public UserController(UserDao dao) {
        this.dao = dao;
    }

    // Backwards-compatible constructor used by existing Main code
    public UserController(SignUp view, UserDao dao) {
        this.dao = dao;
        this.view = view;
        if (this.view != null) {
            this.view.addAddUserListener(e -> {
                String name = view.getFullName().getText();
                String email = view.getEmailField().getText();
                String phone = view.getPhoneNumber().getText();
                String password = view.getPasswordField().getText();
                boolean ok = registerUser(name, email, phone, password);
                if (ok) {
                    javax.swing.JOptionPane.showMessageDialog(view, "Registration successful");
                    // proceed to patient dashboard after successful sign-up
                    view.dispose();
                    view.Dashboard_Patient dashboard = new view.Dashboard_Patient();
                    dashboard.setLocationRelativeTo(null);
                    dashboard.setVisible(true);
                } else {
                    javax.swing.JOptionPane.showMessageDialog(view, "Registration failed");
                }
            });
        }
    }

    /**
     * Registers a new user in the system.
     * @return true if registration succeeded, false otherwise
     */
    public boolean registerUser(String name, String email, String phone, String password) {
        if (name == null || email == null || password == null) return false;
        User user = new User(name, email, phone, password);
        return dao.register(user);
    }
}

    
    
    

