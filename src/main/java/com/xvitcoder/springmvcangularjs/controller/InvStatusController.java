package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcInventStatus;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.InventStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by barmin on 12.01.2017.
 */
@Controller
@RequestMapping("/invstats")
public class InvStatusController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private JdbcInventStatus inventStatusDao = (JdbcInventStatus) context.getBean("inventStatusDAO");

    @RequestMapping("/invstatlist.json")
    public @ResponseBody List<InventStatus> getInvStatList() {
        return inventStatusDao.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    ErrorText addStatus(@RequestBody InventStatus inventStatus) {
        return inventStatusDao.addStatus(inventStatus);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public @ResponseBody ErrorText updateStatus(@RequestBody InventStatus inventStatus) {
        return inventStatusDao.updateStatus(inventStatus);
    }

    @RequestMapping("/layout")
    public String getInvStatsPartialPage() {
            return "invstats/layout";
    }

}

