/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.LoginDao;
import javax.swing.JOptionPane;




/**
 *
 * @author amang
 */

public class LoginController {
    
    // constructor runs here → admin inserted
    private LoginDao loginDao = new LoginDao();
    public static String doc_email;
    public static String doc_name;
    public static String doc_department;
    public static String p_email;

    public String login(String email2, String pass2 ) {

        // Empty field validation
        if (email2 == null || email2.trim().isEmpty() ||
            pass2 == null || pass2.trim().isEmpty()) {

            JOptionPane.showMessageDialog(
                null,
                "Please enter all fields",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            return null;
        }

        String role =  loginDao.login(email2.trim(), pass2.trim());
        
        if (role != null) {
            if ("user".equals(role)) {
                int patientId = loginDao.getPatientIdByEmail(email2.trim());
                String patientName = loginDao.getPatientNameByEmail(email2.trim());
                UserSession.getInstance().setUserData(patientId, email2.trim(), patientName, "user");
            } else if ("doctor".equals(role)) {
                // Set doctor session if needed
            } else if ("admin".equals(role)) {
                // Set admin session if needed
            }
        }
        
        doc_email = loginDao.getCurrentDoctor();
        doc_name = loginDao.getDoctorNameByEmail(doc_email);
        doc_department = loginDao.getDoctorDepartmentByEmail(doc_email);
        p_email = loginDao.getPatientEmail();

        if (role == null) {
            JOptionPane.showMessageDialog(
                null,
                "Invalid email or password",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
        return role;
    }
    public static String currentDoctor(){
       return doc_email;
    }
    
    public static String NameDoctor(){
        return doc_name;
    }
    
    public static String docDepartment(){
        return doc_department;
    }
    public static String pEmail(){
        return p_email;
    }
    
}
