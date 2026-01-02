package dao;

import Database.MySqlConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PatientProfileModel;

/**
 * Data Access Object for Patient operations.
 * Handles database interactions for patient profile management.
 */
public class PatientDao {

    private final MySqlConnection db = new MySqlConnection();

    private static final String SELECT_BY_ID = "SELECT id, name, email, phone FROM users WHERE id = ? LIMIT 1";
    private static final String UPDATE_PATIENT = "UPDATE users SET name = ?, email = ?, phone = ? WHERE id = ?";

    /**
     * Retrieves a patient profile by their ID.
     * 
     * @param id the patient's unique identifier
     * @return PatientProfileModel containing patient data, or null if not found
     * @throws SQLException if database access error occurs
     */
    public PatientProfileModel getPatientById(int id) throws SQLException {
        try (Connection conn = db.getConnection();
                PreparedStatement pst = conn.prepareStatement(SELECT_BY_ID)) {
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    PatientProfileModel p = new PatientProfileModel();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));
                    p.setEmail(rs.getString("email"));
                    p.setPhone(rs.getString("phone"));
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Updates patient profile information.
     * 
     * @param patient the PatientProfileModel with updated information
     * @return true if update was successful, false otherwise
     * @throws SQLException if database access error occurs
     */
    public boolean updatePatient(PatientProfileModel patient) throws SQLException {
        try (Connection conn = db.getConnection();
                PreparedStatement pst = conn.prepareStatement(UPDATE_PATIENT)) {
            pst.setString(1, patient.getName());
            pst.setString(2, patient.getEmail());
            pst.setString(3, patient.getPhone());
            pst.setInt(4, patient.getId());
            return pst.executeUpdate() > 0;
        }
    }
}
