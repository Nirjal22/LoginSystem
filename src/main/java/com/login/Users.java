package com.login;

public class Users {
    private int userId;
    private String username;
    private String passKey;

    public Users(int userId, String username, String passKey) {
        this.userId = userId;
        this.username = username;
        this.passKey = passKey;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
