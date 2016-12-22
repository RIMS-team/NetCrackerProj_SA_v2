package com.xvitcoder.springmvcangularjs.dao;


import com.xvitcoder.springmvcangularjs.model.Employee;

import java.util.List;

/**
 * Created by netcracker on 29.11.2016.
 */
public interface EmployeeDao {
    public void insert(Employee employee);
    public Employee findByEmployeeId(int employeeId);
    public List<Employee> findAllEmployee();
}
