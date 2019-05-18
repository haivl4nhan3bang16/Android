package com.example.detaicuoiky;

public class Model{
    private String userName;
    private String userEmail;
    private String userID;
    private int id;
    private String subject_code;
    private String subject_name;
    private int credits;
    private String description;
    public Model() {
    }

    public Model(String userName, String userEmail, String userID, int id, String subject_code, String subject_name, int credits, String description) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userID = userID;
        this.id = id;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
        this.credits = credits;
        this.description = description;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

}
