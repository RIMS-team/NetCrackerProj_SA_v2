package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcNotebook;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by trvler135 on 23.12.2016.
 */
@Controller
@RequestMapping("/notebook")
public class NotebookController {

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private JdbcNotebook notebookDAO = (JdbcNotebook) context.getBean("notebookDAO");

    @RequestMapping("/all")
    @ResponseBody
    public List<Notebook> getNotebooks(){
        return notebookDAO.findAll();
    }

    @RequestMapping("/layout")
    public String getNotebooksPartialPage() {
        return "notebooks/layout";
    }

}
