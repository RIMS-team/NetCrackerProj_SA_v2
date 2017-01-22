package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.OrderCursor;

import java.util.List;

/**
 * Created by Elina on 11.01.2017.
 */
public interface OrderDAO {
    List<OrderCursor> findAll();
    OrderCursor findById(int id);
    void updateOrder(OrderCursor order);
    void addOrder(OrderCursor order);
    void deleteOrder(int id);
}
