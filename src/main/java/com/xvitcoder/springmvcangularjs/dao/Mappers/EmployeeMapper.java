package com.xvitcoder.springmvcangularjs.dao.Mappers;


import com.xvitcoder.springmvcangularjs.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Admin on 03.12.2016.
 */
public class EmployeeMapper implements RowMapper<Employee> {
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee(
                rs.getInt("EMPLOYEE_ID"),
                rs.getString("PHONE_NUMBER"),
                rs.getString("FULL_NAME"),
                rs.getString("EMAIL")
        );
        return employee;
    }
}
