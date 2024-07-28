package main.bookmyshow.models;

import java.util.UUID;

public class User {

    private UUID userId;
    private String userName;
    private String mobileNumber;
    private String emailId;

    public User(String userName, String mobileNumber, String emailId) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.mobileNumber = mobileNumber;
        this.emailId = emailId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
