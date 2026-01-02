/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.BookAppointmentModel;
import javax.swing.JOptionPane;
import dao.BookAppointmentDao;

/**
 *
 * @author amritchand
 */

public class BookAppointmentController {

    private final BookAppointmentDao appointmentDao4 = new BookAppointmentDao();

    public boolean bookAppointment(BookAppointmentModel appointment1) {

        if (appointment1.getPatientName() == null || appointment1.getPatientName().isEmpty() ||
            appointment1.getPhone() == null || appointment1.getPhone().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All patient fields are required");
            return false;
        }

        boolean success = appointmentDao4.saveAppointment(appointment1);

        if (success) {
            JOptionPane.showMessageDialog(null, "Appointment booked successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to book appointment");
        }

        return success;
    }
    
}

