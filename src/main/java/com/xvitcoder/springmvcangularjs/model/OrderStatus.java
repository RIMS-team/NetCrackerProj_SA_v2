package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by dell on 24-Nov-16.
 */
public class OrderStatus {
    protected int id;
    protected String code;
    protected String name;
    //protected String attrTypeCode;  == 'ORD_STATUS'

    public OrderStatus() {
    }

    public OrderStatus(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public void setAttrTypeCode(String attrTypeCode) {
//        this.attrTypeCode = attrTypeCode;
//    }
//
//    public String getAttrTypeCode() {
//        return attrTypeCode;
//    }

    public String toString() {
        return "";
    }
}