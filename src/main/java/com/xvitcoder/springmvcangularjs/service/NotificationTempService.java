package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.NotificationTemp;

import java.util.List;

/**
 * Created by Admin on 23.01.2017.
 */
public interface NotificationTempService {
    void insertNotifiTemp(NotificationTemp notificationTemp);
    NotificationTemp selectNotifiTemp(int notifi_num,int user_id);
    List<NotificationTemp> getAllDefTemp();
}
