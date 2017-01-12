package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.service.AccessCardService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Kristina on 05.01.2017.
 */
@Controller
@RequestMapping("/accesscards")
public class AccessCardController {

    private Logger logger = Logger.getLogger(AccessCardController.class);

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private AccessCardService accessCardService = (AccessCardService) context.getBean("accessCardServiceImpl");

    @RequestMapping("/accesscardlist.json")
    public @ResponseBody List<AccessCard> getAccessCardList() {
        logger.debug("Request URL: /accesscardlist.json; Entering getAccessCardList()");
        JdbcAccessCard jdbcAccessCard=(JdbcAccessCard) context.getBean("accessCardDAO");
        List<AccessCard> cards=jdbcAccessCard.findAll();
        logger.debug("Response: " + cards);
        return cards;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody void addAccessCard(@RequestBody AccessCard card) {
        logger.debug("Request URL: /add; Entering addAccessCard(" + card + ")");
        System.out.println(card.toString());
        accessCardService.addAccessCard(card);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody void removeCard(@PathVariable int id) {
        logger.debug("Request URL: /remove/" + id + "; Entering removeCard(" + id + ")");
        accessCardService.deleteCard(id);
    }

    @RequestMapping("/layout")
    public String getCardPartialPage() {
        logger.debug("Request URL: /layout; Entering getCardPartialPage()");
        return "accesscards/layout";
    }

}
