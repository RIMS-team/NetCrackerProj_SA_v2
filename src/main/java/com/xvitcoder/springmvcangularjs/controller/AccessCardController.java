package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.Employee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Kristina on 05.01.2017.
 */
@Controller
@RequestMapping("/accesscards")
public class AccessCardController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");


    @RequestMapping("/accesscardlist.json")
    public @ResponseBody List<AccessCard> getAccessCardList() {
        JdbcAccessCard jdbcAccessCard=(JdbcAccessCard) context.getBean("accessCardDAO");
        List<AccessCard> cards=jdbcAccessCard.findAll();
        return cards;
    }

    @RequestMapping("/layout")
    public String getCardPartialPage() {
        return "cards/layout";
    }
}
