package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import com.xvitcoder.springmvcangularjs.model.*;
import com.xvitcoder.springmvcangularjs.service.AccessCardService;
import com.xvitcoder.springmvcangularjs.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.smartcardio.Card;
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

    private List<AccessCard> printList;

    @RequestMapping("/accesscardlist.json")
    public @ResponseBody List<AccessCard> getAccessCardList() {
        logger.debug("Request URL: /accesscards/accesscardlist.json; Entering getAccessCardList()");
        JdbcAccessCard jdbcAccessCard=(JdbcAccessCard) context.getBean("accessCardDAO");
        List<AccessCard> cards=jdbcAccessCard.findAll();
        logger.debug("Response: " + cards);
        return cards;
    }

    @RequestMapping(value = "/find/{statusId}")
    public @ResponseBody List<AccessCard> getCardsByStatus(@PathVariable("statusId") int statusId) {
        logger.debug("Request URL: /accesscards/find/" + statusId + "; Entering getCardsByStatus(id=" + statusId + ")");
        return accessCardService.findByStatus(statusId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody void addAccessCard(@RequestBody AccessCard card) {
        logger.debug("Request URL: /accesscards/add; Entering addAccessCard(card=" + card + ")");
        System.out.println(card.toString());
        accessCardService.addAccessCard(card);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody void updateCard(@RequestBody AccessCard card) {
        logger.debug("Request URL: /accesscards/update; Entering updateCard(card=" + card + ")");
        accessCardService.updateCard(card);
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
    public @ResponseBody ErrorText removeCard(@PathVariable int id) {
        logger.debug("Request URL: /accesscards/remove/" + id + "; Entering removeCard(id=" + id + ")");
        return accessCardService.deleteCard(id);
    }

    @RequestMapping("/layout")
    public String getCardPartialPage() {
        logger.debug("Request URL: /accesscards/layout; Entering getCardPartialPage()");
        return "accesscards/layout";
    }

    @RequestMapping(value = "/DownloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadPDF() {
        return new ModelAndView("pdfView", "listCards", printList);
    }

    @RequestMapping(value = "/sendPrintList", method = RequestMethod.POST)
    public @ResponseBody void getPrintList(@RequestBody List<AccessCard> cardList) {
        printList = cardList;
        System.err.println(printList);
    }

}
