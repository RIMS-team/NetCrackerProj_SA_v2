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
    protected String inventoryNum;

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

    protected Date createDate;
    protected int editorId;
    protected String editorFullName;

    protected int inventoryIdNew;
    protected String inventoryNumNew;

    public OrderCursor() {
    }

    public OrderCursor(int id, long rowNum, Date date, int statusId, String statusName,
                       int inventoryId, String inventoryType, String inventoryNum,
                       String noteName, String noteModel, String noteMemory, String noteSerial,
                       int employeeId, String employeeFullName, String employeeEmail,
                       int userId, String userFullName,
                       Date createDate, int editorId, String editorFullName) {
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
        this.createDate = createDate;
        this.editorId = editorId;
        this.editorFullName = editorFullName;

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

    public String getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(String inventoryNum) {
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

//    public String getUserEmail() {
//        return userEmail;
//    }
//
//    public void setUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getEditorId() {
        return editorId;
    }

    public void setEditorId(int editorId) {
        this.editorId = editorId;
    }

    public String getEditorFullName() {
        return editorFullName;
    }

    public void setEditorFullName(String editorFullName) {
        this.editorFullName = editorFullName;
    }

    public int getInventoryIdNew() {
        return inventoryIdNew;
    }

    public void setInventoryIdNew(int inventoryIdNew) {
        this.inventoryIdNew = inventoryIdNew;
    }

    public String getInventoryNumNew() {
        return inventoryNumNew;
    }

    public void setInventoryNumNew(String inventoryNumNew) {
        this.inventoryNumNew = inventoryNumNew;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderCursor{");
        sb.append("date=").append(date);
        sb.append(", statusId=").append(statusId);
        sb.append(", statusName='").append(statusName).append('\'');
        sb.append(", inventoryId=").append(inventoryId);
        sb.append(", inventoryType='").append(inventoryType).append('\'');
        sb.append(", inventoryNum='").append(inventoryNum).append('\'');
        sb.append(", noteName='").append(noteName).append('\'');
        sb.append(", noteModel='").append(noteModel).append('\'');
        sb.append(", noteMemory='").append(noteMemory).append('\'');
        sb.append(", noteSerial='").append(noteSerial).append('\'');
        sb.append(", employeeId=").append(employeeId);
        sb.append(", employeeFullName='").append(employeeFullName).append('\'');
        sb.append(", employeeEmail='").append(employeeEmail).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", userFullName='").append(userFullName).append('\'');
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", editorId=").append(editorId);
        sb.append(", editorFullName='").append(editorFullName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
