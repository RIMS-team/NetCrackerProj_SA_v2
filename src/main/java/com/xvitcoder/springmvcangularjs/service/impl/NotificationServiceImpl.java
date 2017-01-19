package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcNotebook;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcNotificationDao;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.service.NotificationService;

import java.util.List;

/**
 * Created by Admin on 18.01.2017.
 */
public class NotificationServiceImpl implements NotificationService {

    private JdbcNotificationDao notificationDao;
    @Override
    public List<Notification> findAll() {
        return notificationDao.findAll();
    }

    public void setNotificationDao(JdbcNotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public JdbcNotificationDao getNotificationDao() {
        return notificationDao;
    }
}
