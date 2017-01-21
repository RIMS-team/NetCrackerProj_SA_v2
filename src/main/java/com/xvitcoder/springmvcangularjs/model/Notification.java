package com.xvitcoder.springmvcangularjs.model;

import java.util.Date;

/**
 * Created by dell on 24-Nov-16.
 */
public class Notification extends Entity {
    protected Order order;
    protected Date first;
    protected Date second;
    protected Date third;

    public Notification() {}

    public Notification(int id, Order order, Date first, Date second, Date third) {
        this.id = id;
        this.order = order;
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

    @Override
    public String toString() {
        return "Notification{" +
                "first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
