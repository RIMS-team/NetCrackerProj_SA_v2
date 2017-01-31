package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Admin on 23.12.2016.
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private Logger logger = Logger.getLogger(EmployeeController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private JdbcEmployeeDao jdbcEmployeeDao = (JdbcEmployeeDao) context.getBean("employeeDAO");

    @RequestMapping("/empoyeelist.json")
    public @ResponseBody List<Employee> getEmployeeList() {
        logger.debug("Request URL: /employees/empoyeelist.json; Entering getEmployeeList()");
        List<Employee> employee = jdbcEmployeeDao.findAllEmployee();
        logger.debug("Response: " + employee);
        return employee;
    }

    @RequestMapping("/layout")
    public String getEmpPartialPage() {
        logger.debug("Request URL: /employees/layout; Entering getEmpPartialPage()");
        return "employees/layout";
    }

    @RequestMapping("/getOrders/{id}")
    public @ResponseBody List<OrderCursor> getOrders(@PathVariable int id) {
        logger.debug("Request URL: /employees/getOrders; Entering getOrders()");
        return jdbcEmployeeDao.findOrdersByEmployeeId(id);
    }

}
