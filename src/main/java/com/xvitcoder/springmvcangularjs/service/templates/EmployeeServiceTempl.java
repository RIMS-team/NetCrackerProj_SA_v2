package com.xvitcoder.springmvcangularjs.service.templates;

import com.xvitcoder.springmvcangularjs.model.Employee;

import java.util.Date;
import java.util.List;

/**
 * Created by Elina on 01.12.2016.
 */
public interface EmployeeServiceTempl {
    boolean addEmployee(Employee employee);

    Employee getEmployeeByName(String name);

    Employee getEmployeeByPhone(int number);

    Employee getEmployeeByEmail(String email);

    boolean deleteEmployee(Employee employee);

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesNotebooks(); // all employees who were given laptops

    List<Employee> getEmployeesCards(); // all employees who were given cards

    List<Employee> getDebtors(Date date); // all employees who had to return inventory before the date
}
