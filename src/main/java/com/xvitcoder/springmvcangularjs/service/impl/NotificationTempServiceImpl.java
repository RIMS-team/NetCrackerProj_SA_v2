package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.NotificationTempDao;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.model.NotificationTemp;
import com.xvitcoder.springmvcangularjs.service.NotificationService;
import com.xvitcoder.springmvcangularjs.service.NotificationTempService;

import java.util.List;

/**
 * Created by Admin on 23.01.2017.
 */
public class NotificationTempServiceImpl implements NotificationTempService {

    private NotificationTempDao notificationTempDao;

    @Override
    public void insertNotifiTemp(NotificationTemp notificationTemp) {
        notificationTempDao.insertNotifiTemp(notificationTemp);
    }

    public NotificationTempDao getNotificationTempDao() {
        return notificationTempDao;
    }

    public void setNotificationTempDao(NotificationTempDao notificationTempDao) {
        this.notificationTempDao = notificationTempDao;
    }
}
