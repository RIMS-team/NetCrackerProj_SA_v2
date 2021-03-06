package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trvler135 on 04.01.2017.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private UserService userService = (UserService) context.getBean("userServiceImpl");

    @Autowired private PasswordEncoder passwordEncoder;

    @RequestMapping("/all")
    @ResponseBody
    public List<User> getUsers(){
        logger.debug("Request URL: /user/all; Entering getUsers()");
        logger.debug("Response: " + userService.findAll());
        return userService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ErrorText addUser(@RequestBody User user) {
        logger.debug("Request URL: /user/add; Entering addUser(user=" + user + ")");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.addUser(user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ErrorText updateUser(@RequestBody User user) {
        logger.debug("Request URL: /user/update; Entering updateUser(user=" + user + ")");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ErrorText removeUser(@PathVariable("id") int id) {
        logger.debug("Request URL: /user/remove/" + id + "; Entering removeUser(id=" + id + ")");
        return userService.deleteUser(id);
    }

    @RequestMapping("/layout")
    public String getUsersPartialPage() {
        logger.debug("Request URL: /user/layout; Entering getUsersPartialPage()");
        return "users/layout";
    }

    @RequestMapping("/checkEmail")
    public @ResponseBody boolean checkEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        return user != null;
    }

    @RequestMapping("/getAuthorizedUser")
    public @ResponseBody User getAuthorizedEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(email);
    }

}
