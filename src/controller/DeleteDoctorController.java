/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.DeleteDoctorDao;
import javax.swing.JOptionPane;

/**
 *
 * @author amang
 */

public class DeleteDoctorController {

    private final DeleteDoctorDao dao = new DeleteDoctorDao();

    public boolean confirmAndDelete(String name2,String email_id2) {

        int choice = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to delete Dr. " + name2 + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

        if (choice != JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Doctor deletion canceled");
            return false;
        }

        boolean deleted = dao.deleteByEmail(email_id2);

        if (deleted) {
            JOptionPane.showMessageDialog(null, "Doctor deleted successfully!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Failed to delete doctor.");
            return false;
        }
    }
}

