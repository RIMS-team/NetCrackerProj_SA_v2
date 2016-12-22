package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcOrderStatus;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import com.xvitcoder.springmvcangularjs.service.OrderStatusService;

import java.util.List;

/**
 * Created by dell on 08-Dec-16.
 */
public class OrderStatusServiceImpl implements OrderStatusService {
    private JdbcOrderStatus orderStatusDao;

    public void setOrderStatusDao(JdbcOrderStatus orderStatusDAO) {
        this.orderStatusDao = orderStatusDAO;
    }

    @Override
    public List<OrderStatus> findAll() {
        return orderStatusDao.findAll();
    }

    @Override
    public OrderStatus findById(int id) {
        return orderStatusDao.findById(id);
    }
}
