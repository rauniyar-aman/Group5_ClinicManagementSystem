/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AppointmentDAO;
import model.Appointment;
import view.BookAppointment;

/**
 *
 * @author acer
 */
public class AppointmentController {
private final BookAppointment view;
private final AppointmentDAO dao;


public AppointmentController(BookAppointment view) {
this.view = view;
this.dao = new AppointmentDAO();
}


public void bookAppointment() {
    
Appointment appt = new Appointment(
view.getDepartment(),
view.getDoctor(),
view.getDate(),
view.getTime()
);


boolean success = dao.saveAppointment(appt);
if (success) {
view.showMessage("Appointment booked successfully");
} else {
view.showMessage("Error booking appointment");
    }
  }
}
