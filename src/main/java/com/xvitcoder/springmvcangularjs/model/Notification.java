package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by dell on 24-Nov-16.
 */
public class Notification extends Entity {
    protected Order order;
    protected int notifi_id;
    protected String inv_type;
    protected String inv_num;
    protected String name;
    protected Date dueDate;
    protected Date first;
    protected Date second;
    protected Date third;

    public Notification() {}

    public Notification(int notifi_id,Order order, String inv_type, String inv_num, String name, Date dueDate, Date first, Date second, Date third) {
        this.notifi_id=notifi_id;
        this.order = order;
        this.inv_type = inv_type;
        this.inv_num = inv_num;
        this.name = name;
        this.dueDate = dueDate;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setFirst(Date first) {
        this.first = first;
    }

    public Date getFirst() {
        return first;
    }

    public void setSecond(Date second) {
        this.second = second;
    }

    public Date getSecond() {
        return second;
    }

    public void setThird(Date third) {
        this.third = third;
    }

    public Date getThird() {
        return third;
    }

    public String getInv_type() {
        return inv_type;
    }

    public void setInv_type(String inv_type) {
        this.inv_type = inv_type;
    }

    public int getNotifi_id() {
        return notifi_id;
    }

    public void setNotifi_id(int notifi_id) {
        this.notifi_id = notifi_id;
    }

    public String getInv_num() {
        return inv_num;
    }

    public void setInv_num(String inv_num) {
        this.inv_num = inv_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "order=" + order +
                ", inv_type='" + inv_type + '\'' +
                ", inv_num=" + inv_num +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
