package com.xvitcoder.springmvcangularjs.dao;



import com.xvitcoder.springmvcangularjs.model.MailInformation;
import com.xvitcoder.springmvcangularjs.model.NotificationTemp;

import java.util.List;

/**
 * Created by Admin on 20.01.2017.
 */
public interface NotificationTempDao {
    NotificationTemp findById(int notifi_num,int user_id);
    void insertNotifiTemp(NotificationTemp notificationTemp);
    void updateNotifiTemp(int notifi_num,int user_id);
    void deleteNotifiTemp(int notifi_num,int user_id);
    List<MailInformation> getCursor(int day_1, int day_2, int day_3);
    void updateStatus();
    void registerNotifi(String orderid,int day_1, int day_2, int day_3);
    List<NotificationTemp> getAllDefTemp();
}
