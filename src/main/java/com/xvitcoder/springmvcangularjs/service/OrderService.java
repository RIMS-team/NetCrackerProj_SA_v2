package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;

import java.util.List;

/**
 * Created by Elina on 11.01.2017.
 */
public interface OrderService {
    List<OrderCursor> findAll();
    OrderCursor findById(int id);
    ErrorText updateOrder(OrderCursor order);
    ErrorText addOrder(OrderCursor order);
    ErrorText deleteOrder(OrderCursor order);
}
