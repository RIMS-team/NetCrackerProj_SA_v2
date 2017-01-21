package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by Admin on 21.01.2017.
 */
public class MailInformation {
    int ORDER_ID;
    Date ORD_DATE;
    int INVENTORY_ID;
    String INVENTORY_NAME;
    String INVENTORY_NUM;
    String INVENTORY_EXTRA_PARAM;
    int EMPLOYEE_ID;
    String EMPLOYEE_FUll_NAME;
    String EMPLOYEE_EMAIL;
    int  USER_ID;
    String  USER_FUll_NAME;
    String  USER_EMAIL;
    String  USER_PHONE;
    int  NOTIFICATION_ID;
    int  NOTIFICATION_NUM;
    String  NOTIFICATION_TEMPLATE;

    public MailInformation(int ORDER_ID, Date ORD_DATE, int INVENTORY_ID, String INVENTORY_NAME, String INVENTORY_NUM, String INVENTORY_EXTRA_PARAM, int EMPLOYEE_ID, String EMPLOYEE_FUll_NAME, String EMPLOYEE_EMAIL, int USER_ID, String USER_FUll_NAME, String USER_EMAIL, String USER_PHONE, int NOTIFICATION_ID, int NOTIFICATION_NUM, String NOTIFICATION_TEMPLATE) {
        this.ORDER_ID = ORDER_ID;
        this.ORD_DATE = ORD_DATE;
        this.INVENTORY_ID = INVENTORY_ID;
        this.INVENTORY_NAME = INVENTORY_NAME;
        this.INVENTORY_NUM = INVENTORY_NUM;
        this.INVENTORY_EXTRA_PARAM = INVENTORY_EXTRA_PARAM;
        this.EMPLOYEE_ID = EMPLOYEE_ID;
        this.EMPLOYEE_FUll_NAME = EMPLOYEE_FUll_NAME;
        this.EMPLOYEE_EMAIL = EMPLOYEE_EMAIL;
        this.USER_ID = USER_ID;
        this.USER_FUll_NAME = USER_FUll_NAME;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_PHONE = USER_PHONE;
        this.NOTIFICATION_ID = NOTIFICATION_ID;
        this.NOTIFICATION_NUM = NOTIFICATION_NUM;
        this.NOTIFICATION_TEMPLATE = NOTIFICATION_TEMPLATE;
    }

    public int getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(int ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public Date getORD_DATE() {
        return ORD_DATE;
    }

    public void setORD_DATE(Date ORD_DATE) {
        this.ORD_DATE = ORD_DATE;
    }

    public int getINVENTORY_ID() {
        return INVENTORY_ID;
    }

    public void setINVENTORY_ID(int INVENTORY_ID) {
        this.INVENTORY_ID = INVENTORY_ID;
    }

    public String getINVENTORY_NAME() {
        return INVENTORY_NAME;
    }

    public void setINVENTORY_NAME(String INVENTORY_NAME) {
        this.INVENTORY_NAME = INVENTORY_NAME;
    }

    public String getINVENTORY_NUM() {
        return INVENTORY_NUM;
    }

    public void setINVENTORY_NUM(String INVENTORY_NUM) {
        this.INVENTORY_NUM = INVENTORY_NUM;
    }

    public String getINVENTORY_EXTRA_PARAM() {
        return INVENTORY_EXTRA_PARAM;
    }

    public void setINVENTORY_EXTRA_PARAM(String INVENTORY_EXTRA_PARAM) {
        this.INVENTORY_EXTRA_PARAM = INVENTORY_EXTRA_PARAM;
    }

    public int getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public void setEMPLOYEE_ID(int EMPLOYEE_ID) {
        this.EMPLOYEE_ID = EMPLOYEE_ID;
    }

    public String getEMPLOYEE_FUll_NAME() {
        return EMPLOYEE_FUll_NAME;
    }

    public void setEMPLOYEE_FUll_NAME(String EMPLOYEE_FUll_NAME) {
        this.EMPLOYEE_FUll_NAME = EMPLOYEE_FUll_NAME;
    }

    public String getEMPLOYEE_EMAIL() {
        return EMPLOYEE_EMAIL;
    }

    public void setEMPLOYEE_EMAIL(String EMPLOYEE_EMAIL) {
        this.EMPLOYEE_EMAIL = EMPLOYEE_EMAIL;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_FUll_NAME() {
        return USER_FUll_NAME;
    }

    public void setUSER_FUll_NAME(String USER_FUll_NAME) {
        this.USER_FUll_NAME = USER_FUll_NAME;
    }

    public String getUSER_EMAIL() {
        return USER_EMAIL;
    }

    public void setUSER_EMAIL(String USER_EMAIL) {
        this.USER_EMAIL = USER_EMAIL;
    }

    public String getUSER_PHONE() {
        return USER_PHONE;
    }

    public void setUSER_PHONE(String USER_PHONE) {
        this.USER_PHONE = USER_PHONE;
    }

    public int getNOTIFICATION_ID() {
        return NOTIFICATION_ID;
    }

    public void setNOTIFICATION_ID(int NOTIFICATION_ID) {
        this.NOTIFICATION_ID = NOTIFICATION_ID;
    }

    public int getNOTIFICATION_NUM() {
        return NOTIFICATION_NUM;
    }

    public void setNOTIFICATION_NUM(int NOTIFICATION_NUM) {
        this.NOTIFICATION_NUM = NOTIFICATION_NUM;
    }

    public String getNOTIFICATION_TEMPLATE() {
        return NOTIFICATION_TEMPLATE;
    }

    public void setNOTIFICATION_TEMPLATE(String NOTIFICATION_TEMPLATE) {
        this.NOTIFICATION_TEMPLATE = NOTIFICATION_TEMPLATE;
    }

    @Override
    public String toString() {
        return "MailInformation{" +
                "ORDER_ID=" + ORDER_ID +
                ", ORD_DATE=" + ORD_DATE +
                ", INVENTORY_ID=" + INVENTORY_ID +
                ", INVENTORY_NAME='" + INVENTORY_NAME + '\'' +
                ", INVENTORY_NUM='" + INVENTORY_NUM + '\'' +
                ", INVENTORY_EXTRA_PARAM='" + INVENTORY_EXTRA_PARAM + '\'' +
                ", EMPLOYEE_ID=" + EMPLOYEE_ID +
                ", EMPLOYEE_FUll_NAME='" + EMPLOYEE_FUll_NAME + '\'' +
                ", EMPLOYEE_EMAIL='" + EMPLOYEE_EMAIL + '\'' +
                ", USER_ID=" + USER_ID +
                ", USER_FUll_NAME='" + USER_FUll_NAME + '\'' +
                ", USER_EMAIL='" + USER_EMAIL + '\'' +
                ", USER_PHONE='" + USER_PHONE + '\'' +
                ", NOTIFICATION_ID=" + NOTIFICATION_ID +
                ", NOTIFICATION_NUM=" + NOTIFICATION_NUM +
                ", NOTIFICATION_TEMPLATE='" + NOTIFICATION_TEMPLATE + '\'' +
                '}';
    }
}
