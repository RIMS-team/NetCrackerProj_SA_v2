package com.xvitcoder.springmvcangularjs.dao;


import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * Created by dell on 08-Dec-16.
 */
public interface OrderStatusDao {
    List<OrderStatus> findAll();
    OrderStatus findById(int id);
    ErrorText addStatus(OrderStatus status);
    ErrorText updateStatus(OrderStatus status);
}
