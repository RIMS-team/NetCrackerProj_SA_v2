package com.xvitcoder.springmvcangularjs.dao.Mappers;

import com.xvitcoder.springmvcangularjs.model.MailInformation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 21.01.2017.
 */
public class MailInformationMapper implements RowMapper<MailInformation> {
    @Override
    public MailInformation mapRow(ResultSet resultSet, int i) throws SQLException {
        MailInformation mailInformation = new MailInformation(resultSet.getInt("ORDER_ID"),
                resultSet.getDate("ORD_DATE"),
                resultSet.getInt("INVENTORY_ID"),
                resultSet.getString("INVENTORY_NAME"),
                resultSet.getString("INVENTORY_NUM"),
                resultSet.getString("INVENTORY_EXTRA_PARAM"),
                resultSet.getInt("EMPLOYEE_ID"),
                resultSet.getString("EMPLOYEE_FUll_NAME"),
                resultSet.getString("EMPLOYEE_EMAIL"),
                resultSet.getInt("USER_ID"),
                resultSet.getString("USER_FUll_NAME"),
                resultSet.getString("USER_EMAIL"),
                resultSet.getString("USER_PHONE"),
                resultSet.getInt("NOTIFICATION_ID"),
                resultSet.getInt("NOTIFICATION_NUM"),
                resultSet.getString("NOTIFICATION_TEMPLATE")
                );
        return mailInformation;
    }
}
