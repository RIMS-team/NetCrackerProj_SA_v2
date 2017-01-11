package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by barmin on 23.11.2016.
 */
public class Employee extends Entity {

    String phoneNumber;
    String fullName;
    String eMail;

    public Employee(){}

    public Employee(int id, String phoneNumber, String fullName, String eMail) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.eMail = eMail;
    }

    public Employee(String s, String s2, String s23) {
    }

    public String toString(){
        return super.toString() + " phoneNumber = " + phoneNumber + " fullName = " + fullName + " eMail = " + eMail;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
