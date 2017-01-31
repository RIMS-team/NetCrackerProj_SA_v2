package com.xvitcoder.springmvcangularjs.dao.Mappers;

import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by trvler135 on 31.01.2017.
 */
public class OrderMiniMapper implements RowMapper<OrderCursor> {
    @Override
    public OrderCursor mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderCursor order = new OrderCursor(
                rs.getInt("ORDER_ID"),
                rs.getDate("DUE_DATE"),
                rs.getString("INVENTORY_TYPE"),
                rs.getString("INVENTORY_NUM")
        );
        return order;
    }
}
