package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.MailSettings;
import com.xvitcoder.springmvcangularjs.service.MailSettingsService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Stas on 29.01.2017.
 */
@Controller
@RequestMapping("/mailsettings")
public class MailSettingsController {
    private Logger logger = Logger.getLogger(UserController.class);


    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private MailSettingsService mailSettingsService = (MailSettingsService) context.getBean("mailSettingsService");

    @RequestMapping("/getMailSettings")
    public @ResponseBody
    MailSettings getMailSettings() {
        logger.debug("Request URL: /mailSettings/getMailSettings; Entering getMailSettings()");
        return mailSettingsService.getMailSettings();
    }

    @RequestMapping(value = "/updateMailSettings", method = RequestMethod.PUT)
    public @ResponseBody void updateMailSettings(@RequestBody MailSettings mailSettings) {
        logger.debug("Request URL: /mailSettings/updateMailSettings; Entering updateMailSettings(mailSettings =" + mailSettings + ")");
        mailSettingsService.updateMailSettings(mailSettings);
    }

    @RequestMapping("/layout")
    public String getUsersSettingsPartialPage() {
        return "mailsettings/layout";
    }
}
