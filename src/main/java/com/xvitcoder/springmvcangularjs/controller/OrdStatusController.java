package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcInventStatus;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcOrderStatus;
import com.xvitcoder.springmvcangularjs.model.InventStatus;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by barmin on 13.01.2017.
 */
@Controller
@RequestMapping("/ordstats")
public class OrdStatusController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private JdbcOrderStatus orderStatusDao = (JdbcOrderStatus) context.getBean("orderStatusDAO");

    @RequestMapping("/ordstatlist.json")
    public @ResponseBody List<OrderStatus> getOrdStatList() {
        return orderStatusDao.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody void addStatus(@RequestBody OrderStatus orderStatus) {
        orderStatusDao.addStatus(orderStatus);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody void updateStatus(@RequestBody OrderStatus orderStatus) {
        orderStatusDao.updateStatus(orderStatus);
    }

    @RequestMapping("/layout")
    public String getOrdStatsPartialPage() {
        return "ordstats/layout";
    }

}
