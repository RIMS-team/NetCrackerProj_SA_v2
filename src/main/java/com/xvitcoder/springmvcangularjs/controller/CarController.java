package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.service.CarService;
import com.xvitcoder.springmvcangularjs.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xvitcoder
 * Date: 12/21/12
 * Time: 12:23 AM
 */
@Controller
@RequestMapping("/cars")
public class CarController {

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    @Autowired
    private CarService carService;

    /*
    JdbcEmployeeDao jdbcEmployeeDao=(JdbcEmployeeDao) context.getBean("employeeDAO");
    List<Employee> employee=jdbcEmployeeDao.findAllEmployee();

    @RequestMapping("/carlist.json")
    public @ResponseBody List<Employee> getCarList() {
        return employee;
    }

    @RequestMapping(value = "/addCar/{car}", method = RequestMethod.POST)
    public @ResponseBody void addCar(@PathVariable("car") String car) {
        carService.addCar(car);
    }

    @RequestMapping(value = "/removeCar/{car}", method = RequestMethod.DELETE)
    public @ResponseBody void removeCar(@PathVariable("car") String car) {
        carService.deleteCar(car);
    }

    @RequestMapping(value = "/removeAllCars", method = RequestMethod.DELETE)
    public @ResponseBody void removeAllCars() {
        carService.deleteAll();
    }

    @RequestMapping("/layout")
    public String getCarPartialPage() {
        return "cars/layout";
    }
    */

    @RequestMapping("/carlist.json")
    public @ResponseBody List<String> getCarList() {
        return carService.getAllCars();
    }

    @RequestMapping(value = "/addCar/{car}", method = RequestMethod.POST)
    public @ResponseBody void addCar(@PathVariable("car") String car) {
        carService.addCar(car);
    }

    @RequestMapping(value = "/removeCar/{car}", method = RequestMethod.DELETE)
    public @ResponseBody void removeCar(@PathVariable("car") String car) {
        carService.deleteCar(car);
    }

    @RequestMapping(value = "/removeAllCars", method = RequestMethod.DELETE)
    public @ResponseBody void removeAllCars() {
        carService.deleteAll();
    }

    @RequestMapping("/layout")
    public String getCarPartialPage() {
        return "cars/layout";
    }
}
