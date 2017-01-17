package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.InventStatusDao;
import com.xvitcoder.springmvcangularjs.dao.JdbcEmployeeDao;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcInventStatus;
import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.model.InventStatus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by barmin on 12.01.2017.
 */
    @Controller
    @RequestMapping("/invstats")
    public class InvStatusController {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");


        @RequestMapping("/invstatlist.json")
        public @ResponseBody
        List<InventStatus> getInvStatList() {
            JdbcInventStatus inventStatusDao = (JdbcInventStatus) context.getBean("inventStatusDAO");
            List<InventStatus> invStats = inventStatusDao.findAll();
            return invStats;
        }

        @RequestMapping("/layout")
        public String getInvStatsPartialPage() {
            return "invstats/layout";
        }
    }

