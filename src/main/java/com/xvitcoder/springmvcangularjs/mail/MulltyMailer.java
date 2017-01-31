package com.xvitcoder.springmvcangularjs.mail;

import com.xvitcoder.springmvcangularjs.dao.NotificationTempDao;
import com.xvitcoder.springmvcangularjs.model.MailInformation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Admin on 21.01.2017.
 */
public class MulltyMailer implements Runnable {

    private EmailSender emailSender;
    private ApplicationContext context;
    private NotificationTempDao notificationService;

    public MulltyMailer(EmailSender emailSender){
        this.emailSender=emailSender;
        this.context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        this.notificationService=(NotificationTempDao) context.getBean("notificationTempDAO");
    }

    @Override
    public void run() {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
                NotificationTempDao notificationService=(NotificationTempDao) context.getBean("notificationTempDAO");
                notificationService.updateStatus();
            }
        });
        thread.start();
        List<MailInformation> list=notificationService.getCursor(0,3,7);
        StringBuilder stringBuilder=new StringBuilder();
        for(MailInformation mailInformation:list) {
            emailSender.sendMessage(emailSender.getMailTo().getEmail(),emailSender.getMailTo().getPassword(),mailInformation.getEMPLOYEE_EMAIL(),mailInformation.getNOTIFICATION_TEMPLATE());
            stringBuilder.append(mailInformation.getORDER_ID()+",");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        System.out.println(stringBuilder.toString());
        notificationService.registerNotifi(stringBuilder.toString(),0,3,7);
    }
}
