package com.xvitcoder.springmvcangularjs.dao.Mappers;



import com.xvitcoder.springmvcangularjs.model.AccessCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Kristina on 05.12.2016.
 */
public class AccessCardMapper implements RowMapper<AccessCard> {

    public AccessCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccessCard accessCard = new AccessCard(
                rs.getInt("OBJECT_ID"),
                rs.getInt("INV_STATUS_ID"),
                rs.getString("INV_STATUS_NAME"),
                rs.getString("INVENTORY_NUM"),
                rs.getString("EMPLOYEE_FULL_NAME"),
                rs.getDate("OPEN_DATE"),
                rs.getDate("DUE_DATE")
        );
        return accessCard;
    }
}
