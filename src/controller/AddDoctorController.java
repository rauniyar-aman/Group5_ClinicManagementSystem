/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package controller;

import dao.AddDoctorDao;
import model.AddDoctorModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author amang
 */


public class AddDoctorController {

    private AddDoctorDao addDoctorDao = new AddDoctorDao();

    public boolean addDoctor(String doctorName, String email,String docPass,
                             String mobile, String specialization) {

        // Basic empty check
        if (doctorName.isEmpty() || email.isEmpty() || docPass.isEmpty()||
            mobile.isEmpty() || specialization.isEmpty()){

            JOptionPane.showMessageDialog(null, "All fields are required!");
            return false;
        }

        try {
            // Create model
            AddDoctorModel doctor = new AddDoctorModel();
            doctor.setDoctorName(doctorName);
            doctor.setEmail(email);
            doctor.setPassword(docPass);
            doctor.setMobile(mobile);
            doctor.setSpecialization(specialization);

            // Save to DB
            addDoctorDao.addDoctor(doctor);

            JOptionPane.showMessageDialog(null, "Doctor added successfully!");
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return false;
        }
    }
}
