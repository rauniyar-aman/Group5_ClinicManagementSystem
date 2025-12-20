/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database1.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
/**
 *
 * @author acer
 */
public class AppointmentDAO {
    public boolean saveAppointment(Appointment appt) {
try {
Connection con = DBConnection.getConnection();
String sql = "INSERT INTO appointments(department, doctor, date, time) VALUES (?,?,?,?)";
PreparedStatement ps = con.prepareStatement(sql);


ps.setString(1, appt.getDepartment());
ps.setString(2, appt.getDoctor());
ps.setString(3, appt.getDate());
ps.setString(4, appt.getTime());


ps.executeUpdate();
return true;
} catch (Exception e) {
e.printStackTrace();
return false;
}
}

    public boolean saveAppointment(model.Appointment appt) {
    }

    private static class Appointment {

        public Appointment() {
        }

        private String getDepartment() {
        }

        private String getDoctor() {
        }

        private String getDate() {
        }

        private String getTime() {
        }
    }
    
}
