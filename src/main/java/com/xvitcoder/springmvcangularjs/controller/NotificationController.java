package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.service.NotificationService;
import com.xvitcoder.springmvcangularjs.service.impl.NotebookServiceImpl;
import com.xvitcoder.springmvcangularjs.service.impl.NotificationServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.01.2017.
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {

    private Logger logger = Logger.getLogger(OrderController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private NotificationService notificationService=(NotificationServiceImpl) context.getBean("notificationServiceImpl");


    @RequestMapping("/all")
    @ResponseBody
    public List<Notification> getNotebooks(){
        logger.debug("Request URL: /notebook/all; Entering getNotebooks()");
        logger.debug("Response: " + notificationService.findAll());
        return notificationService.findAll();
    }

    @RequestMapping("/layout")
    public String getOrdersPartialPage() {
        logger.debug("Request URL: /order/layout; Entering getOrdersPartialPage()");
        return "notifications/layout";
    }
}
