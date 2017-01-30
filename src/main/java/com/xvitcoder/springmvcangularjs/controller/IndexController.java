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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.SecureRandom;
import java.util.Random;

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
    private EmailSender emailSender = (EmailSender) context.getBean("mailImpl");


    @Autowired private PasswordEncoder passwordEncoder;

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

    @RequestMapping("/restore")
    public String goRestorePassword () {
        return "restore";
    }


    private String generateRandomPassword() {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
        Random RANDOM = new SecureRandom();
        StringBuilder pw = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = (int)(RANDOM.nextDouble() * letters.length());
            pw.append(letters.substring(index, index + 1));
        }
        return pw.toString();
    }

    @RequestMapping("/newpass")
    public String generateNewPass (@RequestParam("email") String email) {
        EmailSender emailSender = new EmailSender();
        User user = userService.findByEmail(email);
        if (user != null) {
            String newPass = generateRandomPassword();
            user.setPassword(passwordEncoder.encode(newPass));
            userService.updateUser(user);
            emailSender.sendMessage("v.karpov2018@yandex.ru","q1w2e3r4t1",email,"Greetings! Your password has been reset to '" + newPass + "'");
        }
        return "redirect:/login?newpass=true";
    }



    @Scheduled(cron = "0 0 1 * * ?")
    public void doSomething() {
        MulltyMailer mulltyMailer = new MulltyMailer(emailSender);
        Thread thread = new Thread(mulltyMailer);
        thread.start();
    }

}
