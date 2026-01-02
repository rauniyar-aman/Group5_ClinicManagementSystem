package model;

/**
 * Model class representing a patient's profile information.
 * Contains patient identification and contact details.
 */
public class PatientProfileModel {
    private int id;
    private String name;
    private String email;
    private String phone;

    /**
     * Gets the patient's unique identifier.
     * 
     * @return the patient ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the patient's unique identifier.
     * 
     * @param id the patient ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the patient's full name.
     * 
     * @return the patient name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the patient's full name.
     * 
     * @param name the patient name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the patient's email address.
     * 
     * @return the patient email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the patient's email address.
     * 
     * @param email the patient email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the patient's phone number.
     * 
     * @return the patient phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the patient's phone number.
     * 
     * @param phone the patient phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PatientProfileModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
