package com.xvitcoder.springmvcangularjs.dao.Mappers;

import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 18.01.2017.
 */
public class NotificationMapper implements RowMapper<Notification> {

    @Override
    public Notification mapRow(ResultSet resultSet, int i) throws SQLException {
        Notification notification=new Notification(resultSet.getInt("NOTIFICATION_ID"),
                new Order(resultSet.getInt("ORDER_ID"),null,null,null,null,null),resultSet.getDate("FIRST_DATE"),
                resultSet.getDate("SECOND_DATE"),resultSet.getDate("THIRD_DATE"));
        return notification;
    }
}
