package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class User {
    private int userId;
    private String userName;
    private String password;
    private  String name;
    private long phoneNumber;
    private String email;
    private String date;

    public User() {
    }

    public User(int userId, String userName, String password, String name, long phoneNumber, String email, String date) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
