package com.xvitcoder.springmvcangularjs.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: xvitcoder
 * Date: 12/20/12
 * Time: 5:27 PM
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private Logger logger = Logger.getLogger(IndexController.class);

    @RequestMapping
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping("/login")
    public String getLogin() {
        logger.debug("Request URL: /login; Entering getLogin()");
        return "login";
    }

}
