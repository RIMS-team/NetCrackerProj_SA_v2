package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.service.NotebookService;
import com.xvitcoder.springmvcangularjs.service.impl.NotebookServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by trvler135 on 23.12.2016.
 */
@Controller
@RequestMapping("/notebook")
public class NotebookController {

    private Logger logger = Logger.getLogger(NotebookController.class);

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private NotebookService notebookService = (NotebookServiceImpl) context.getBean("notebookServiceImpl");

    private List<Notebook> printList;

    @RequestMapping("/all")
    @ResponseBody
    public List<Notebook> getNotebooks(){
        logger.debug("Request URL: /notebook/all; Entering getNotebooks()");
        logger.debug("Response: " + notebookService.findAll());
        return notebookService.findAll();
    }

    @RequestMapping("/layout")
    public String getNotebooksPartialPage() {
        logger.debug("Request URL: /notebook/layout; Entering getNotebooksPartialPage()");
        return "notebooks/layout";
    }

    @RequestMapping(value = "/DownloadPDF", method = RequestMethod.GET)
    public ModelAndView downloadPDF() {
        return new ModelAndView("pdfView", "listNotebook", printList);
    }

    @RequestMapping(value = "/sendPrintList", method = RequestMethod.POST)
    public @ResponseBody void getPrintList(@RequestBody List<Notebook> noteList) {
        printList = noteList;
        System.err.println(printList);
    }

}
