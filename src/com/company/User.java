package com.company;

public class User {
    private String username;
    private String passwordHash;
    private boolean isOnline;

    public User(String username, String password) {
        this.username = username;
        this.passwordHash = PasswordHashing.hashPassword(password);
        this.isOnline = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean isOnLine() {
        return isOnline;
    }

    public void setOnLine(boolean online) {
        isOnline = online;
    }
}
