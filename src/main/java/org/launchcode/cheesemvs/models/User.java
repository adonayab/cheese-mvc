package org.launchcode.cheesemvs.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    private int userId;
    private String userName;
    private String password;
    private String email;
    private static int nextId = 1;

    public User() {
        userId = nextId;
        nextId++;
    }

    public User(String userName, String password, String email) {
        this();
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        User.nextId = nextId;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
