package com.xvitcoder.springmvcangularjs.dao.Mappers;


import com.xvitcoder.springmvcangularjs.model.InventStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dell on 08-Dec-16.
 */
public class InventStatusMapper implements RowMapper<InventStatus> {
    @Override
    public InventStatus mapRow(ResultSet resultSet, int i) throws SQLException {
        InventStatus inventStatus = new InventStatus(
                resultSet.getInt("ID"),
                resultSet.getString("CODE"),
                resultSet.getString("NAME")
        );
        return inventStatus;
    }
}
