package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by Elina on 11.01.2017.
 */
public class OrderCursor extends Entity {
    protected long rowNum;
    protected Date date;

    protected int statusId;
    protected String statusName;

    protected int inventoryId;
    protected String inventoryType;
    protected long inventoryNum;

    protected String noteName;
    protected String noteModel;
    protected String noteMemory;
    protected String noteSerial;

    protected int employeeId;
    protected String employeeFullName;
    protected String employeeEmail;

    protected int userId;
    protected String userFullName;
    protected String userEmail;

    public OrderCursor() {
    }

    public OrderCursor(int id, long rowNum, Date date, int statusId, String statusName,
                       int inventoryId, String inventoryType, long inventoryNum,
                       String noteName, String noteModel, String noteMemory, String noteSerial,
                       int employeeId, String employeeFullName, String employeeEmail,
                       int userId, String userFullName, String userEmail) {
        this.id = id;
        this.rowNum = rowNum;
        this.date = date;
        this.statusId = statusId;
        this.statusName = statusName;
        this.inventoryId = inventoryId;
        this.inventoryType = inventoryType;
        this.inventoryNum = inventoryNum;
        this.noteName = noteName;
        this.noteModel = noteModel;
        this.noteMemory = noteMemory;
        this.noteSerial = noteSerial;
        this.employeeId = employeeId;
        this.employeeFullName = employeeFullName;
        this.employeeEmail = employeeEmail;
        this.userId = userId;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
    }

    public long getRowNum() {
        return rowNum;
    }

    public void setRowNum(long rowNum) {
        this.rowNum = rowNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public long getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(long inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteModel() {
        return noteModel;
    }

    public void setNoteModel(String noteModel) {
        this.noteModel = noteModel;
    }

    public String getNoteMemory() {
        return noteMemory;
    }

    public void setNoteMemory(String noteMemory) {
        this.noteMemory = noteMemory;
    }

    public String getNoteSerial() {
        return noteSerial;
    }

    public void setNoteSerial(String noteSerial) {
        this.noteSerial = noteSerial;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}