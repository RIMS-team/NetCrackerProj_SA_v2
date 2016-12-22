package com.xvitcoder.springmvcangularjs.dao.Mappers;


import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dell on 08-Dec-16.
 */
public class OrderStatusMapper  implements RowMapper<OrderStatus> {

    @Override
    public OrderStatus mapRow(ResultSet resultSet, int i) throws SQLException {
        OrderStatus orderStatus = new OrderStatus(
                resultSet.getInt("ID"),
                resultSet.getString("CODE"),
                resultSet.getString("NAME")
        );
        return orderStatus;
    }
}
