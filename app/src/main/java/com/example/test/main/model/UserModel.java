package com.example.test.main.model;

public class UserModel {
    String name;
    String emailId;
    String Password;
    String UserUuid;

    public UserModel(String name, String emailId, String password, String userUuid) {
        this.name = name;
        this.emailId = emailId;
        Password = password;
        UserUuid = userUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserUuid() {
        return UserUuid;
    }

    public void setUserUuid(String userUuid) {
        UserUuid = userUuid;
    }
}
