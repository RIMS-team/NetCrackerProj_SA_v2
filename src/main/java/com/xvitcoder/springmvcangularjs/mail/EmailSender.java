package com.xvitcoder.springmvcangularjs.mail;

import com.xvitcoder.springmvcangularjs.dao.NotificationTempDao;
import com.xvitcoder.springmvcangularjs.model.Mail;
import com.xvitcoder.springmvcangularjs.model.MailInformation;
import com.xvitcoder.springmvcangularjs.model.MailSettings;
import com.xvitcoder.springmvcangularjs.service.MailSettingsService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Java Program to send text mail using default SMTP server and without authentication.
 * You need mail.jar, smtp.jar and activation.jar to run this program.
 *
 * @author Javin Paul
 */

public class EmailSender {




    public Properties getMailProperty(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        MailSettingsService mailSettingsService = (MailSettingsService) context.getBean("mailSettingsService");
        MailSettings mailSettings=mailSettingsService.getMailSettings();
        Properties properties=new Properties();
        properties.put("mail.smtp.host",mailSettings.getHost());
        properties.put("mail.smtp.socketFactory.port",mailSettings.getSocketFactoryPort());
        properties.put("mail.smtp.socketFactory.class",mailSettings.getSocketFactoryClass());
        properties.put("mail.smtp.auth",mailSettings.getAuth());
        properties.put("mail.smtp.port",mailSettings.getPort());
        return properties;
    }

    public Mail getMailTo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        MailSettingsService mailSettingsService = (MailSettingsService) context.getBean("mailSettingsService");
        MailSettings mailSettings=mailSettingsService.getMailSettings();
        Mail mail=new Mail(mailSettings.getFrom(),mailSettings.getPassword());
        return mail;
    }


    private Logger logger = Logger.getLogger(EmailSender.class);

    public void sendMessage(final String whoSend, final String password, String whoCheck,String mess){
        logger.debug("Entering sendMessage(whoSend=" + whoSend + ", password=" + password + ", whoCheck=" + whoCheck + ")");
        Properties properties=getMailProperty();

        Session session=Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(whoSend,password);
            }
        });


        try {
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress("v.karpov2018@yandex.ru"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(whoCheck));
            message.setSubject("Netcracker");
            message.setText(mess);

            Transport.send(message);

            System.out.println(4);
        } catch (MessagingException e) {
            logger.error("Error sending message", e);
        }

    }

}
