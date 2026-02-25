package model.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import model.address.IAddress;

public class User {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String jobTitle;
    private int age;
    private ArrayList<IAddress> addresses;
    private LocalDateTime lastLoginDate;

    public User(String name, String lastName, String email, String password, String jobTitle, int age) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.jobTitle = jobTitle;
        this.age = age;
        this.addresses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<IAddress> getAddresses() {
        return addresses;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
    




}

