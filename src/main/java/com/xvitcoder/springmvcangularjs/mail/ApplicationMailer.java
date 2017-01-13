package com.xvitcoder.springmvcangularjs.mail;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

public class ApplicationMailer {

    private Logger logger = Logger.getLogger(JdbcAccessCard.class);

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage preConfiguredMessage;

    /**
     * This method will send compose and send the message
     * */
    public void sendMail(String to, String subject, String body)
    {
        logger.debug("Entering sendMail(to=" + to + ", subject=" + subject + ", body=" + body + ")");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    /**
     * This method will send a pre-configured message
     * */
    public void sendPreConfiguredMail(String message)
    {
        logger.debug("Entering sendPreConfiguredMail(message=" + message + ")");
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }

}