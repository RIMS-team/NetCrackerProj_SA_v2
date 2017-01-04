package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.NotebookService;
import com.xvitcoder.springmvcangularjs.service.UserService;
import com.xvitcoder.springmvcangularjs.service.impl.NotebookServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by trvler135 on 04.01.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private UserService userService = (UserService) context.getBean("userServiceImpl");

    @RequestMapping("/all")
    @ResponseBody
    public List<User> getUsers(){
        for (User user : userService.findAll()) {
            System.out.println(user.toString());
        }
        return userService.findAll();
    }

    @RequestMapping("/layout")
    public String getUsersPartialPage() {
        return "users/layout";
    }
}
