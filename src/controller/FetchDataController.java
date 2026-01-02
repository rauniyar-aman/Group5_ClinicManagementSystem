/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.FetchDataDao;
import javax.swing.JOptionPane;


/**
 *
 * @author amang
 */
public class FetchDataController {
    
    FetchDataDao customerDao = new FetchDataDao();
    

    public String[] getfetchByEmail(String email_id1) {
        if (email_id1.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an email ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String[] details = customerDao.fetchByEmail(email_id1);
        if (details == null) {
            JOptionPane.showMessageDialog(null, "No doctor found with email: " + email_id1, "Not Found", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            return details;
        }
    return null;
    }   
}
