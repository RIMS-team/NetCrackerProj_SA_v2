package com.xvitcoder.springmvcangularjs.controller;

import com.xvitcoder.springmvcangularjs.service.ImportService;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/import-csv/{id}/{type}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> importCSV(@RequestParam("file") MultipartFile file, @PathVariable int id, @PathVariable String type) {
        logger.debug("Request URL: /import-csv-emp; Entering importCSVEmp(file=" + file.getOriginalFilename()
                + ", id=" + id + ", type=" + type + ")");

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

                return new ResponseEntity<>(importService.fileProcessing(serverFile.getAbsolutePath(), id, type), HttpStatus.OK);
            } catch (Exception e) {
                logger.debug("Failed to upload " + file.getOriginalFilename(), e);
                return new ResponseEntity<>("Failed to upload "   + file.getOriginalFilename(), HttpStatus.OK);
            }
        } else {
            logger.debug("Failed to upload " + file.getOriginalFilename()
                    + " because the file was empty.");
            return new ResponseEntity<>("Failed to upload " + file.getOriginalFilename()
                    + " because the file was empty", HttpStatus.OK);
        }
    }

}
