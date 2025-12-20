/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import view.SignUp;
import javax.swing.*;
import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import model.UserData1;
/**
 *
 * @author acer
 */
public class UserController {
    private final  SignUp view;
    private final UserDao dao;
    SignUp userView = new SignUp();
    UserDao userdao = new UserDao();

    public UserController(SignUp view) {
        this.view = view;
        this.dao = new Userdao();

        /*view.getBtnSignup().addActionListeviewner(e -> registerUser());*/
    }

    public UserController(SignUp signUpView, UserDao userDao) {
        this.userView = userView;
        

        userView.addConfirmListener(new ConfirmActionListner());
        userView.AddSignUpListener(new Sign)
    }

    public boolean registerUser() {
        // Get data from SignUp view
        String name = view.getTxtName().getText();
        String email = view.getTxtEmail().getText();
        String phone = view.getTxtPhone().getText();
        String password = new String(view.getTxtPassword().getPassword());
        
        // Create User model
        User user = new User(name, email, phone, password );

        // Save to DB using UserDao
        boolean success = dao.register(user);

        return success;
    }

    public void open() {
       userView.setVisible(true);
    }

    public void close() {
        userView.dispose();
    }

    private static class Userdao extends UserDao {

        public Userdao() {
        }
    }

    class ConfirmActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ex) {
            try {
                String phoneNumber = userView.getTxtPhone().getText();
                String email = userView.getTxtEmail().getText();
                String fullName = userView.getTxtName().getText();
                String password = userView.getTxtPassword().getText();
                

                UserData1 userdata = new UserData1(phoneNumber, email, fullName, password);

                boolean exists = userdao.checkUser(userdata);
                if (exists) {
                    JOptionPane.showMessageDialog(userView,
                            "User already exists with this email or mobile number.");
                } else {
                    userdao.SignUp(userdata);
                    JOptionPane.showMessageDialog(userView,
                            "Registration successful! Please log in.");

                    LoginView loginView = new LoginView();
                    LoginController loginController = new LoginController(loginView);

                    close();             
                    loginController.open();
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(userView, "Error: " + e.getMessage());
            }
        }
    }

    

    private class userView {

        private static void setVisible(boolean b) {
        }

        private static void dispose() {
        }

        private static Object getMobileNumber() {
            
        }

        public userView() {
        }
    }
} 

    
    
    

