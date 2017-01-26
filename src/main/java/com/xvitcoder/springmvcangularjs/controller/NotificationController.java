package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.model.NotificationTemp;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.NotificationService;
import com.xvitcoder.springmvcangularjs.service.NotificationTempService;
import com.xvitcoder.springmvcangularjs.service.impl.NotebookServiceImpl;
import com.xvitcoder.springmvcangularjs.service.impl.NotificationServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.01.2017.
 */
@Controller
@RequestMapping("/notification")
public class NotificationController {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        NotificationService notificationService=(NotificationServiceImpl) context.getBean("notificationServiceImpl");
        NotificationTempService notificationTempService=(NotificationTempService) context.getBean("notificationTempService");
        List<NotificationTemp> notificationTemp=notificationTempService.getAllDefTemp();
        System.out.println(notificationTemp.get(1));


    }

    private Logger logger = Logger.getLogger(OrderController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private NotificationService notificationService=(NotificationServiceImpl) context.getBean("notificationServiceImpl");
    private NotificationTempService notificationTempService=(NotificationTempService) context.getBean("notificationTempService");


    @RequestMapping("/all")
    @ResponseBody
    public List<Notification> getNotebooks(){
        logger.debug("Request URL: /notebook/all; Entering getNotebooks()");
        return notificationService.findAllNotifi();
    }

    @RequestMapping("/allDefTemp")
    @ResponseBody
    public List<NotificationTemp> getAllDefTemp(){
        logger.debug("Request URL: /notebook/all; Entering getNotebooks()");
        logger.debug("Response: " + notificationService.findAll());
        List<NotificationTemp> temps=notificationTempService.getAllDefTemp();
        System.out.println(temps.get(1).toString());
        return temps;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public @ResponseBody void insertNotificationTemp(@RequestBody NotificationTemp notificationTemp) {
        notificationTempService.insertNotifiTemp(notificationTemp);
    }

    @RequestMapping("/layout")
    public String getOrdersPartialPage() {
        logger.debug("Request URL: /order/layout; Entering getOrdersPartialPage()");
        return "notifications/layout";
    }

    @RequestMapping("/findNotifiById/{id}")
    public @ResponseBody NotificationTemp getNotifiTemp(@PathVariable("id") int id){
        return notificationTempService.selectNotifiTemp(id,0);
    }
}
