package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by Kristina on 23.11.2016.
 */
public class Notebook extends Inventory {

    protected String name;
    protected String location;
    protected String memoryType;
    protected String model;
    //protected int additionalFinanceNumber;
    protected String serialNumber;

    public Notebook(){}

    public Notebook(int id, int statusId, String statusName, String name, String location, String memoryType,
                    String model, String inventoryNum, String serialNumber, String employeeName, Date openDate, Date dueDate) {
        this.id = id;
        this.statusId = statusId;
        this.statusName = statusName;
        this.name = name;
        this.location = location;
        this.memoryType = memoryType;
        this.model = model;
        this.inventoryNum = inventoryNum;
        this.serialNumber = serialNumber;
        this.employeeName = employeeName;
        this.openDate = openDate;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMemoryType() {
        return memoryType;
    }
    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getModel() {
        return model;
    }

//    public int getAdditionalFinanceNumber() {
//        return additionalFinanceNumber;
//    }

    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Notebook{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(statusName).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", memoryType='").append(memoryType).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", inventoryNum=").append(inventoryNum);
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
