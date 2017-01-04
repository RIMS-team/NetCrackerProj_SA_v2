package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Vic on 24-Nov-16.
 */
public class User extends Employee {

    protected String password;

    public User(){}

    public User(int id, String phoneNumber, String fullName, String eMail, String password){
        super(id, phoneNumber, fullName, eMail);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return super.toString() + " password=" + password;
    }
}
