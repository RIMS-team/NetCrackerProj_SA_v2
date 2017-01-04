package com.xvitcoder.springmvcangularjs.dao.Mappers;


import com.xvitcoder.springmvcangularjs.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by trvler135 on 06.12.2016.
 */
public class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(
                rs.getInt("EMPLOYEE_ID"),
                rs.getString("PHONE_NUMBER"),
                rs.getString("FULL_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD")
        );
        return user;
    }
}

