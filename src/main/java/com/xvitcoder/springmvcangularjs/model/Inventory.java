package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Kristina on 23.11.2016.
 */
abstract class Inventory extends Entity {

    protected String status;

    public Inventory() {
        throw new IllegalArgumentException();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
