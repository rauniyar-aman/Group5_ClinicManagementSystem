/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.PostDao;
import model.DoctorAvailability;
import javax.swing.JOptionPane;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author amritchand
 */
public class PostController {
    

    private PostDao postDao = new PostDao();

    public void saveDoctorAvailability(String name, String email, String dateStr, String timeStr,String specialization ) {
        try {
            Date date = Date.valueOf(dateStr); // yyyy-MM-dd
            Time time = Time.valueOf(timeStr); // HH:mm:ss

            DoctorAvailability availability = new DoctorAvailability(name, email, date, time, specialization );

            boolean saved = postDao.saveAvailability(availability);
            if (saved) {
                JOptionPane.showMessageDialog(null, "Availability saved successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save availability.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid date/time format! Use yyyy-MM-dd and HH:mm:ss");
        }
    }
}
