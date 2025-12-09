/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import Model.UserData;
import dao.UserDao;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.SignUp;

/**
 *
 * @author acer
 */
public class UserController {
 
    private final UserDao userdao = new UserDao();
    private final SignUp signUpView;
      public UserController(SignUp signUpView) {
        this.signUpView = signUpView;

        // Add event listener to SIGNUP BUTTON
        signUpView.addSignUpListener(new SignUpAction());
    }

    public void open() {
        signUpView.setVisible(true);
    }

    public void close() {
        signUpView.dispose();
    }

    class SignUpAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                // Collect data from your SignUp.java fields
               

                // Basic validations
                if (email.equals("") || email.equals("Enter your email")
                        || phone.equals("") || phone.equals("Enter your phone number")
                        || name.equals("") || name.equals("Enter your name")
                        || password.equals("") || password.equals("Enter your password")
                        || confirm.equals("") || confirm.equals("Enter your password")) {

                    JOptionPane.showMessageDialog(signUpView, "Please fill all fields!");
                    return;
                }

                if (!password.equals(confirm)) {
                    JOptionPane.showMessageDialog(signUpView, "Passwords do not match!");
                    return;
                }

                // Create UserData object
                UserData userdata = new UserData(name, email, password);

                // Check if user already exists
                boolean exists = userdao.check(userdata);

                if (exists) {
                    JOptionPane.showMessageDialog(signUpView, "User already exists!");
                } else {
                    userdao.SignUp(userdata);
                    JOptionPane.showMessageDialog(signUpView, "Signup Successful!");

                    // Open Login Page
                    login loginView = new login();
                    LoginController logController = new LoginController(loginView);

                    close();          // close signup
                    logController.open(); // open login
                }

            } catch (HeadlessException ex) {
                System.out.println("Error in UserController: " + ex);
            }
        }
    }

}
