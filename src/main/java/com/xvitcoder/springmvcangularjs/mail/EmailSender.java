package com.xvitcoder.springmvcangularjs.mail;

import com.xvitcoder.springmvcangularjs.dao.NotificationTempDao;
import com.xvitcoder.springmvcangularjs.model.MailInformation;
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

//    public static void main(String[] args) {
//        EmailSender emailSender=new EmailSender();
//        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//        NotificationTempDao notificationService=(NotificationTempDao) context.getBean("notificationTempDAO");
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//                NotificationTempDao notificationService=(NotificationTempDao) context.getBean("notificationTempDAO");
//                notificationService.updateStatus();
//            }
//        });
//        thread.start();
//        List<MailInformation> list=notificationService.getCursor(0,3,7);
//        StringBuilder stringBuilder=new StringBuilder();
//        for(MailInformation mailInformation:list) {
//            //emailSender.sendMessage("v.karpov2018@yandex.ru","q1w2e3r4t1",mailInformation.getEMPLOYEE_EMAIL(),mailInformation.getNOTIFICATION_TEMPLATE());
//            stringBuilder.append(mailInformation.getORDER_ID()+",");
//        }
//        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
//        System.out.println(stringBuilder.toString());
//        notificationService.registerNotifi(stringBuilder.toString(),0,3,7);
//    }

    private Logger logger = Logger.getLogger(EmailSender.class);

    public void sendMessage(final String whoSend, final String password, String whoCheck,String mess){
        logger.debug("Entering sendMessage(whoSend=" + whoSend + ", password=" + password + ", whoCheck=" + whoCheck + ")");
        Properties properties=new Properties();
        properties.put("mail.smtp.host","smtp.yandex.ru");
        properties.put("mail.smtp.socketFactory.port","465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.port","465");

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
