/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.sql.Time;

/**
 *
 * @author amritchand
 */

public class DoctorAvailability {
    private int id;
    private String name;
    private String email;
    private Date availableDate;
    private Time availableTime;
    private String specialization;
    
    public DoctorAvailability() {}

    public DoctorAvailability(String name, String email, Date availableDate, Time availableTime, String specialization) {
        this.name = name;
        this.email = email;
        this.availableDate = availableDate;
        this.availableTime = availableTime;
        this.specialization = specialization;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public Date getAvailableDate() { return availableDate; }
    public Time getAvailableTime() { return availableTime; }
    public String getSpecialization() { return specialization; }
    
    public void setId(int id) { this.id = id; }
    public void setDoctorName(String name) { this.name = name; }
    public void setEmail(String email) {
       this.email = email;
    }
    public void setDepartment(String specialization) { this.specialization = specialization; }
    public void setAvailableDate(Date availableDate) { this.availableDate = availableDate; }
    public void setAvailableTime(Time availableTime) { this.availableTime = availableTime; }
    
}
