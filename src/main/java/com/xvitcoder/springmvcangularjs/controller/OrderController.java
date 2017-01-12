package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import com.xvitcoder.springmvcangularjs.service.OrderService;
import com.xvitcoder.springmvcangularjs.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Elina on 11.01.2017.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private OrderService orderService = (OrderServiceImpl) context.getBean("orderServiceImpl");

    @RequestMapping("/all")
    @ResponseBody
    public List<OrderCursor> getOrders(){
        return orderService.findAll();
    }

    @RequestMapping("/layout")
    public String getOrdersPartialPage() {
        return "orders/layout";
    }

}