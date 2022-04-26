package com.example.spuntech;

public class User {

    private String username;
    private String firstname;
    private String lastname;
    private String pass;
    private String phone;
    private String level;
    private String email;

    public User(String username, String firstname, String lastname, String pass, String phone, String email, String level) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.pass = pass;
        this.phone = phone;
        this.email = email;
        this.level = level;
    }


    public User() {
    }

    public User(String username, String firstname, String lastname) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}



