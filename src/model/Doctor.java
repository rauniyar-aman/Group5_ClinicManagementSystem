/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Doctor POJO - Represents doctor entity from database
 * 
 * @author Group 5
 */
public class Doctor {

    private int doctorId;
    private String doctorName;
    private String email;
    private String mobile;
    private String specialization;
    private String status;

    // Constructors
    public Doctor() {
    }

    public Doctor(int doctorId, String doctorName, String email, String mobile, String specialization, String status) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.email = email;
        this.mobile = mobile;
        this.specialization = specialization;
        this.status = status;
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", specialization='" + specialization + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
