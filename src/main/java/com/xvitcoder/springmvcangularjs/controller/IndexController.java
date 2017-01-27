package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.mail.EmailSender;
import com.xvitcoder.springmvcangularjs.mail.MulltyMailer;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: xvitcoder
 * Date: 12/20/12
 * Time: 5:27 PM
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = Logger.getLogger(IndexController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private UserService userService = (UserService) context.getBean("userServiceImpl");

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin() {
        logger.debug("Request URL: /login; Entering getLogin()");
        return "login";
    }

    @RequestMapping("/adminpass")
    @ResponseBody
    public String generateAdminPass() {
        User user = userService.findByEmail("evgeniy@gmail.com");
        String encodedPass = passwordEncoder.encode("123456");
        user.setPassword(encodedPass);
        userService.updateUser(user);
        return "123456";
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void doSomething() {
        MulltyMailer mulltyMailer = new MulltyMailer(new EmailSender());
        Thread thread = new Thread(mulltyMailer);
        thread.start();
    }

}
