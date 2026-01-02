/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import Database.MySqlConnection;
import model.DoctorAvailability;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import model.BookAppointmentModel;


/**
 *
 * @author amritchand
 */

public class AppointmentDao {

    private final MySqlConnection db = new MySqlConnection();

    public List<DoctorAvailability> fetchAllAvailability() {

        List<DoctorAvailability> list = new ArrayList<>();

        String sql = "SELECT name, id ,specialization, available_date, available_time, email FROM doctor_availability";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DoctorAvailability d = new DoctorAvailability();
                d.setDoctorName(rs.getString("name"));
                d.setId(rs.getInt("id"));
                d.setDepartment(rs.getString("specialization"));
                d.setAvailableDate(rs.getDate("available_date"));
                d.setAvailableTime(rs.getTime("available_time"));
                d.setEmail(rs.getString("email"));

                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    

    public List<DoctorAvailability> fetchAppointmentsByEmail(String email8) {
        List<DoctorAvailability> list = new ArrayList<>();
        String sql = "SELECT appointment_id, doctor_name, doc_department, appointment_date, appointment_time " +
                     "FROM appointment WHERE patient_email = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email8);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DoctorAvailability d = new DoctorAvailability();
                d.setId(rs.getInt("appointment_id"));
                d.setDoctorName(rs.getString("doctor_name"));
                d.setDepartment(rs.getString("doc_department"));
                d.setAvailableDate(rs.getDate("appointment_date"));
                d.setAvailableTime(rs.getTime("appointment_time"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<BookAppointmentModel> fetchAppointmentsByDoctorEmail(String email20) {
        List<BookAppointmentModel> list = new ArrayList<>();
        String sql = "SELECT appointment_id, patient_name, patient_phone, appointment_date, appointment_time " +
                     "FROM appointment WHERE doctor_email = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email20);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BookAppointmentModel d = new BookAppointmentModel();
                d.setAppointmentId(rs.getInt("appointment_id"));
                d.setPatientName(rs.getString("patient_name"));
                d.setPhone(rs.getString("patient_phone"));
                d.setAppointmentDate(rs.getDate("appointment_date"));
                d.setAppointmentTime(rs.getString("appointment_time"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
