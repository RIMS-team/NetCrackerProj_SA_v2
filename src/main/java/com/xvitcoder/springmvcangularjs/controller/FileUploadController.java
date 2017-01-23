package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.service.ImportService;
import com.xvitcoder.springmvcangularjs.service.impl.CSVImportService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Kristina on 17.01.2017.
 */
@Controller
public class FileUploadController {

    private Logger logger = Logger.getLogger(FileUploadController.class);
    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    private ImportService importService = (ImportService) context.getBean("csvImportService");

    @RequestMapping(value = "/import-csv/{type}", method = RequestMethod.POST)
    public @ResponseBody
    void importCSV(@RequestParam("file") MultipartFile file, @PathVariable String type) {
        logger.debug("Request URL: /import-csv-emp; Entering importCSVEmp(file=" + file.getOriginalFilename()
                + ", type=" + type + ")");

        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
                logger.debug("You successfully uploaded file=" + file.getOriginalFilename());

                importService.fileProcessing(serverFile.getAbsolutePath(), type);
            } catch (Exception e) {
                logger.debug("Failed to upload " + file.getOriginalFilename(), e);
            }
        } else {
            logger.debug("Failed to upload " + file.getOriginalFilename()
                    + " because the file was empty.");
        }
    }

}
