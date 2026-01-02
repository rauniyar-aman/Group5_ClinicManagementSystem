package controller;

import View.Patient_Edit_Info;
import dao.PatientDao;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import model.PatientProfileModel;

public class PatientProfileController {
    private final Patient_Edit_Info view;
    private final PatientDao dao = new PatientDao();

    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public PatientProfileController(Patient_Edit_Info view) {
        this.view = view;
    }

    public void loadProfile() {
        Integer patientId = UserSession.getInstance().getPatientId();
        if (patientId == null) {
            view.showMessage("Please login again to load your profile.");
            return;
        }
        try {
            PatientProfileModel patient = dao.getPatientById(patientId);
            if (patient == null) {
                view.showMessage("No patient record found.");
                return;
            }
            view.setPatientData(patient.getName(), patient.getEmail(), patient.getPhone());
        } catch (SQLException ex) {
            view.showMessage("Failed to load profile: " + ex.getMessage());
        }
    }

    public void updateProfile() {
        Integer patientId = UserSession.getInstance().getPatientId();
        if (patientId == null) {
            view.showMessage("Session expired. Please login again.");
            return;
        }

        String name = view.getNameInput();
        String email = view.getEmailInput();
        String phone = view.getMobileInput();

        if (name.isBlank() || email.isBlank() || phone.isBlank()) {
            view.showMessage("All fields are required.");
            return;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            view.showMessage("Enter a valid email address.");
            return;
        }

        PatientProfileModel patient = new PatientProfileModel();
        patient.setId(patientId);
        patient.setName(name);
        patient.setEmail(email);
        patient.setPhone(phone);

        try {
            boolean updated = dao.updatePatient(patient);
            if (updated) {
                JOptionPane.showMessageDialog(view, "Profile updated successfully.");
                loadProfile();
            } else {
                view.showMessage("Nothing was updated.");
            }
        } catch (SQLException ex) {
            view.showMessage("Update failed: " + ex.getMessage());
        }
    }
}
