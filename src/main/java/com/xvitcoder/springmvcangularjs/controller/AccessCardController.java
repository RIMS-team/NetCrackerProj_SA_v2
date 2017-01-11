package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.AccessCardService;
import com.xvitcoder.springmvcangularjs.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.smartcardio.Card;
import java.util.List;

/**
 * Created by Kristina on 05.01.2017.
 */
@Controller
@RequestMapping("/accesscards")
public class AccessCardController {
    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private AccessCardService accessCardService = (AccessCardService) context.getBean("accessCardServiceImpl");

    @RequestMapping("/accesscardlist.json")
    public @ResponseBody List<AccessCard> getAccessCardList() {
        JdbcAccessCard jdbcAccessCard=(JdbcAccessCard) context.getBean("accessCardDAO");
        List<AccessCard> cards=jdbcAccessCard.findAll();
        return cards;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody void addUser(@RequestBody AccessCard card) {
        System.out.println(card.toString());
        accessCardService.addUser(card);
    }


    @RequestMapping("/layout")
    public String getCardPartialPage() {
        return "accesscards/layout";
    }
}
