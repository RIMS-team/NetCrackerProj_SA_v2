package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.beans.RailwayStation;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.NotebookService;
import com.xvitcoder.springmvcangularjs.service.UserService;
import com.xvitcoder.springmvcangularjs.service.impl.NotebookServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody void addUser(@RequestBody User user) {
        System.out.println(user.toString());
        userService.addUser(user);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void removeUser(@PathVariable("id") int id) {
        // TODO: user removal;
    }

    @RequestMapping("/layout")
    public String getUsersPartialPage() {
        return "users/layout";
    }
}
