package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcOrder;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import com.xvitcoder.springmvcangularjs.service.OrderService;

import java.util.List;

/**
 * Created by Elina on 11.01.2017.
 */
public class OrderServiceImpl implements OrderService {
    private JdbcOrder orderDao;

    public void setOrderDao(JdbcOrder orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<OrderCursor> findAll() {
        return orderDao.findAll();
    }

    @Override
    public OrderCursor findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public ErrorText updateOrder(OrderCursor order) {
        return orderDao.updateOrder(order);
    }

    @Override
    public ErrorText addOrder(OrderCursor order) {
        return orderDao.addOrder(order);
    }

    @Override
    public ErrorText deleteOrder(OrderCursor order) {
        return orderDao.deleteOrder(order);
    }
}

