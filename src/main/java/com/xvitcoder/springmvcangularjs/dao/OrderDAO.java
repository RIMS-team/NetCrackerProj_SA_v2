package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;

import java.util.List;

/**
 * Created by Elina on 11.01.2017.
 */
public interface OrderDAO {
    List<OrderCursor> findAll();
    OrderCursor findById(int id);
    ErrorText updateOrder(OrderCursor order);
    ErrorText addOrder(OrderCursor order);
    ErrorText deleteOrder(int id);
}
