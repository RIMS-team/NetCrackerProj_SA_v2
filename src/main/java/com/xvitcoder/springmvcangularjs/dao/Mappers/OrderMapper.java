package com.xvitcoder.springmvcangularjs.dao.Mappers;

import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Elina on 12.01.2017.
 */
public class OrderMapper implements RowMapper<OrderCursor> {
    @Override
    public OrderCursor mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderCursor order = new OrderCursor(
                rs.getInt("ORDER_ID"),
                rs.getLong("RN"),
                rs.getDate("DATE_VALUE"),
                rs.getInt("ORD_STATUS_ID"),
                rs.getString("ORD_STATUS_NAME"),
                rs.getInt("INVENTORY_ID"),
                rs.getString("INVENTORY_TYPE"),
                rs.getLong("INVENTORY_NUM"),
                rs.getString("NOTE_NAME"),
                rs.getString("NOTE_MODEL"),
                rs.getString("NOTE_MEMORY_TYPE"),
                rs.getString("NOTE_SERIAL_NUMBER"),
                rs.getInt("EMPLOYEE_ID"),
                rs.getString("EMPLOYEE_FULL_NAME"),
                rs.getString("EMPLOYEE_EMAIL"),
                rs.getInt("USER_ID"),
                rs.getString("USER_FULL_NAME"),
                rs.getString("USER_FULL_NAME")
        );
        return order;
    }
}
