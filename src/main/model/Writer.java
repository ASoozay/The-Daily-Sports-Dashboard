package main.model;

public class Writer {

    private int writerId;
    private String role;
    private String firstName;
    private String lastName;
    private String standing;
    private java.sql.Date dateOfHire;
    private String email;
    private String phoneNumber;
    
    public Writer(int writerId, String role, String firstName, String lastName, String standing, java.sql.Date dateOfHire, String email, String phoneNumber) {
        this.writerId = writerId;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.standing = standing;
        this.dateOfHire = dateOfHire;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public int getWriterId() {
        return writerId;
    }
    public String getRole() {
        return role;
    }       
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getStanding() {
        return standing;
    }
    public java.sql.Date getDateOfHire() {
        return dateOfHire;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setRole(String role) {
        this.role = role;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setStanding(String standing) {
        this.standing = standing;
    }
    public void setDateOfHire(java.sql.Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
