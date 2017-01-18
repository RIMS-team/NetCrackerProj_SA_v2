package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Kristina on 23.11.2016.
 */
public class AccessCard extends Inventory {

    public AccessCard() {}

    public AccessCard(int id, int statusId, String statusName, long inventoryNum) {
        this.id = id;
        this.statusId = statusId;
        this.statusName = statusName;
        this.inventoryNum = inventoryNum;
    }

    public long getInventoryNum() {
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

