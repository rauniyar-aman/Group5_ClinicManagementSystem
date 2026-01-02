/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.BookAppointmentModel;
import java.sql.Connection;
import Database.MySqlConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author amritchand
 */

public class BookAppointmentDao {
    
    private final MySqlConnection db = new MySqlConnection();

    public boolean saveAppointment(BookAppointmentModel appointment1) {
        String sql = "INSERT INTO appointment (patient_email, patient_name, patient_phone, doctor_name, doc_department, appointment_date, appointment_time, doctor_email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {
            
            pst.setString(1, appointment1.getPatientEmail());
            pst.setString(2, appointment1.getPatientName());
            pst.setString(3, appointment1.getPhone());
            pst.setString(4, appointment1.getDoctorName());
            pst.setString(5, appointment1.getSpecialization());
            pst.setDate(6, new java.sql.Date(appointment1.getAppointmentDate().getTime()));
            pst.setString(7, appointment1.getAppointmentTime());
            pst.setString(8, appointment1.getDoctorEmail());

            

            int row = pst.executeUpdate();
            return row > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}


