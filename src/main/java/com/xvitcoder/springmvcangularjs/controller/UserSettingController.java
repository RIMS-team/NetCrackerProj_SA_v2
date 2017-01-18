package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.Admin;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Admin on 17.01.2017.
 */
@Controller
@RequestMapping("/userSetting")
public class UserSettingController {



    private Logger logger = Logger.getLogger(UserController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private UserService userService = (UserService) context.getBean("userServiceImpl");

    @RequestMapping("/getAuthorizedUser")
    public @ResponseBody
    User getAuthorizedEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(email);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody void updateUser(@RequestBody User user) {
        logger.debug("Request URL: /user/update; Entering updateUser(user=" + user + ")");
        userService.updateUser(user);
    }


    @RequestMapping("/layout")
    public String getUsersSettingsPartialPage() {
        return "settings/layout";
    }

}
