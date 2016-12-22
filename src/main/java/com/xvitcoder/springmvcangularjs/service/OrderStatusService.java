package com.xvitcoder.springmvcangularjs.service;



import com.xvitcoder.springmvcangularjs.model.OrderStatus;

import java.util.List;

/**
 * Created by dell on 08-Dec-16.
 */
public interface OrderStatusService {
    List<OrderStatus> findAll();
    OrderStatus findById(int id);
}
