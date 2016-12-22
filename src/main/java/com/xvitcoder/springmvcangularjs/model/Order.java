package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by Vic on 24-Nov-16.
 */
public class Order extends Entity {
    protected OrderStatus orderStatus;
    protected User user;
    protected Employee employee;
    protected Date date;
    protected Inventory inventory;

    public Order() {}

    public Order(int id, OrderStatus orderStatus, User user, Employee employee, Date date, Inventory inventory) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.user = user;
        this.employee = employee;
        this.date = date;
        this.inventory = inventory;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String toString() {
        ///ToDo
        return super.toString() + " orderStatus=" + orderStatus;
    }
}
