/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Appointment {
  private String department;
private String doctor;
private String date;
private String time;


public Appointment(String department, String doctor, String date, String time) {
this.department = department;
this.doctor = doctor;
this.date = date;
this.time = time;
}


public String getDepartment() { return department; }
public String getDoctor() { return doctor; }
public String getDate() { return date; }
public String getTime() { return time; }
}  

