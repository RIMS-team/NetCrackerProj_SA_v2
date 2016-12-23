package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Admin on 23.12.2016.
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");


    @RequestMapping("/empoyeelist.json")
    public @ResponseBody List<Employee> getEmployeeList() {
        JdbcEmployeeDao jdbcEmployeeDao=(JdbcEmployeeDao) context.getBean("employeeDAO");
        List<Employee> employee=jdbcEmployeeDao.findAllEmployee();
        return employee;
    }

    @RequestMapping("/layout")
    public String getEmpPartialPage() {
        return "employees/layout";
    }
}
