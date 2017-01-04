package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Vic on 24-Nov-16.
 */
public class Admin extends User {

    public Admin(){}

    public Admin(int id, String phoneNumber, String fullName, String eMail, String password){
        super(id, phoneNumber, fullName, eMail, password);
    }

    public String toString() {
        return super.toString();
    }
}
