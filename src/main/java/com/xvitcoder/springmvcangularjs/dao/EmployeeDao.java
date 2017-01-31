package com.xvitcoder.springmvcangularjs.dao;


import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;

import java.util.List;

/**
 * Created by netcracker on 29.11.2016.
 */
public interface EmployeeDao {
    void insert(Employee employee);
    Employee findByEmployeeId(int employeeId);
    List<Employee> findAllEmployee();
    List<OrderCursor> findOrdersByEmployeeId(int id);
}
