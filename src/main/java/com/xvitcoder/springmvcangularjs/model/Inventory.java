package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Kristina on 23.11.2016.
 */
class Inventory extends Entity {

    protected int statusId;
    protected String statusName;
    protected long inventoryNum;

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

    public long getInventoryNum() {
        return inventoryNum;
    }
}
