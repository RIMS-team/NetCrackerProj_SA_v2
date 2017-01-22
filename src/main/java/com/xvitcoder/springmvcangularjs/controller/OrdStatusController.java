package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcOrderStatus;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by barmin on 13.01.2017.
 */
@Controller
@RequestMapping("/ordstats")
public class OrdStatusController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");


    @RequestMapping("/ordstatlist.json")
    public @ResponseBody
    List<OrderStatus> getOrdStatList() {
        JdbcOrderStatus orderStatusDao = (JdbcOrderStatus) context.getBean("orderStatusDAO");
        List<OrderStatus> ordStats = orderStatusDao.findAll();
        return ordStats;
    }

    @RequestMapping("/layout")
    public String getOrdStatsPartialPage() {
        return "ordstats/layout";
    }
}
