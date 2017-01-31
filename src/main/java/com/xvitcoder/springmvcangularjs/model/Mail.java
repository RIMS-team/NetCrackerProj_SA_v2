package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Admin on 30.01.2017.
 */
public class Mail {
    String email;
    String password;

    public Mail(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
