package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by Kristina on 23.11.2016.
 */
public class AccessCard extends Inventory {

    public AccessCard() {}

    public AccessCard(int id, int statusId, String statusName, String inventoryNum, String employeeName, Date openDate, Date dueDate) {
        this.id = id;
        this.statusId = statusId;
        this.statusName = statusName;
        this.inventoryNum = inventoryNum;
        this.employeeName = employeeName;
        this.openDate = openDate;
        this.dueDate = dueDate;
    }

    public String getInventoryNum() {
        return inventoryNum;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccessCard{");
        sb.append("id=").append(id).append('\'');
        sb.append(", statusId=").append(statusId).append('\'');
        sb.append(", statusName=").append(statusName).append('\'');
        sb.append(", inventoryNum=").append(inventoryNum);
        sb.append('}');
        return sb.toString();
    }
}

