/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DoctorDAO;
import model.Doctor;
import java.util.ArrayList;
import java.util.List;

/**
 * DoctorController - Business logic layer for Doctor operations
 * Intermediary between View and DAO
 * 
 * @author Group 5
 */
public class DoctorController {

    private final DoctorDAO doctorDAO;

    public DoctorController() {
        this.doctorDAO = new DoctorDAO();
    }

    /**
     * Fetch all doctors from the database
     * 
     * @return List of all Doctor objects
     */
    public List<Doctor> fetchAllDoctors() {
        try {
            return doctorDAO.getAllDoctors();
        } catch (Exception e) {
            System.err.println("Error in DoctorController.fetchAllDoctors: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
