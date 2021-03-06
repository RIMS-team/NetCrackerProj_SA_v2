package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import com.xvitcoder.springmvcangularjs.service.OrderService;
import com.xvitcoder.springmvcangularjs.service.impl.OrderServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Elina on 11.01.2017.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    private Logger logger = Logger.getLogger(OrderController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private OrderService orderService = (OrderServiceImpl) context.getBean("orderServiceImpl");

    @RequestMapping("/all")
    @ResponseBody
    public List<OrderCursor> getOrders(){
        logger.debug("Request URL: /order/all; Entering getOrders()");
//        logger.debug("Response: " + orderService.findAll());
        return orderService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ErrorText addOrder(@RequestBody OrderCursor order) {
        logger.debug("Request URL: /order/add; Entering addOrder(order=" + order + ")");
        System.out.println(order.toString());
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody ErrorText updateOrder(@RequestBody OrderCursor order) {
        logger.debug("Request URL: /order/update; Entering updateOrder(order=" + order + ")");
        return orderService.updateOrder(order);
    }

//    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
//    public @ResponseBody ErrorText removeOrder(@PathVariable int id) {
//        logger.debug("Request URL: /order/remove/" + id + "; Entering removeOrder(id=" + id + ")");
//        return orderService.deleteOrder(id);
//    }
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public @ResponseBody ErrorText removeOrder(@RequestBody OrderCursor order) {
        logger.debug("Request URL: /order/remove; Entering removeOrder(order=" + order + ")");
        return orderService.deleteOrder(order);
    }

    @RequestMapping("/layout")
    public String getOrdersPartialPage() {
        logger.debug("Request URL: /order/layout; Entering getOrdersPartialPage()");
        return "orders/layout";
    }
}