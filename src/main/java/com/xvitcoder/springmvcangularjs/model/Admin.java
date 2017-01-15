package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Vic on 24-Nov-16.
 */
public class Admin extends User {

    protected String role;

    public Admin(){}

    public Admin(int id, String phoneNumber, String fullName, String eMail, String password, String role){
        super(id, phoneNumber, fullName, eMail, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toString() {
        return super.toString();
    }
}
