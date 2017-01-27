package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by Kristina on 23.11.2016.
 */
class Inventory extends Entity {

    protected int statusId;
    protected String statusName;
    protected String inventoryNum;
    protected String employeeName;
    protected Date dueDate;
    protected Date openDate;

    public Inventory() {
        //throw new IllegalArgumentException();
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
