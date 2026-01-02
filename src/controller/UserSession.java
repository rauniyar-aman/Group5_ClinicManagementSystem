package controller;

/**
 * Singleton class to manage user session data across the application.
 * Stores currently logged-in user information including patient ID, email,
 * name, and role.
 * 
 * @author Professional Java Swing Developer
 */
public class UserSession {

    private static UserSession instance;

    private Integer patientId;
    private String userEmail;
    private String userName;
    private String userRole;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private UserSession() {
        // Private constructor for singleton pattern
    }

    /**
     * Gets the singleton instance of UserSession.
     * 
     * @return the UserSession instance
     */
    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * Initializes the session with user data.
     * 
     * @param patientId the patient's database ID
     * @param email     the user's email address
     * @param name      the user's full name
     * @param role      the user's role (admin, doctor, user)
     */
    public void setUserData(Integer patientId, String email, String name, String role) {
        this.patientId = patientId;
        this.userEmail = email;
        this.userName = name;
        this.userRole = role;
    }

    /**
     * Clears all session data (used during logout).
     */
    public void clearSession() {
        this.patientId = null;
        this.userEmail = null;
        this.userName = null;
        this.userRole = null;
    }

    /**
     * Gets the current patient's ID.
     * 
     * @return the patient ID, or null if not set
     */
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID.
     * 
     * @param patientId the patient ID to set
     */
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the current user's email.
     * 
     * @return the user email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the user's email.
     * 
     * @param userEmail the email to set
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Gets the current user's name.
     * 
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's name.
     * 
     * @param userName the name to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets the current user's role.
     * 
     * @return the user role (admin, doctor, user)
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the user's role.
     * 
     * @param userRole the role to set
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * Checks if a user is currently logged in.
     * 
     * @return true if user data exists, false otherwise
     */
    public boolean isLoggedIn() {
        return userEmail != null && !userEmail.isEmpty();
    }
}
