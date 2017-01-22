package com.xvitcoder.springmvcangularjs.dao.Mappers;

import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.model.NotificationTemp;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 21.01.2017.
 */
public class NotificationTempMapper implements RowMapper<NotificationTemp> {
    @Override
    public NotificationTemp mapRow(ResultSet resultSet, int i) throws SQLException {
        NotificationTemp notificationTemp = new NotificationTemp(0,
                0,
                resultSet.getString("template"));
        return notificationTemp;
    }
}
