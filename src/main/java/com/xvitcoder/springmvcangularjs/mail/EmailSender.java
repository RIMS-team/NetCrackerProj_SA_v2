package com.xvitcoder.springmvcangularjs.mail;

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

public class EmailSender{

    public void sendMessage(final String whoSend, final String password, String whoCheck){

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
            message.setSubject("Netckraker");
            message.setText("Вам пришло уведомление!");

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}
