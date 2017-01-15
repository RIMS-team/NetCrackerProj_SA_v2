package com.xvitcoder.springmvcangularjs.dao.Mappers;

import com.xvitcoder.springmvcangularjs.model.Admin;
import com.xvitcoder.springmvcangularjs.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by trvler135 on 15.01.2017.
 */
public class AdminMapper implements RowMapper<Admin> {

    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin(
                rs.getInt("EMPLOYEE_ID"),
                rs.getString("PHONE_NUMBER"),
                rs.getString("FULL_NAME"),
                rs.getString("EMAIL"),
                rs.getString("PASSWORD"),
                rs.getString("ROLE")
        );
        return admin;
    }
}
