package com.example.loginregisterapplication;



public class User {
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private String reg_date;

    public static User[] users = {
            new User("nur", "Nurzhakhan", "Galymova", "nur", "12/11/2021")
    };

    public User(String email, String first_name, String last_name, String password, String reg_date) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.password = password;
        this.reg_date = reg_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public String getFullName() {
        return first_name + " " + last_name;
    }

    public static User[] getUsers() {
        return users;
    }

    public static void setUsers(User[] users) {
        User.users = users;
    }
}

