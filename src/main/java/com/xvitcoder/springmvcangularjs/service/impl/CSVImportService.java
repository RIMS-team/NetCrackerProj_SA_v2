package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.ImportDao;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcImport;
import com.xvitcoder.springmvcangularjs.service.ImportService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Kristina on 17.01.2017.
 */

public class CSVImportService implements ImportService {

    private Logger logger = Logger.getLogger(CSVImportService.class);

    private ImportDao importDao;

    public void setImportDao(ImportDao importDao) {
        this.importDao = importDao;
    }

    public String fileProcessing(String filePath, int userId, String type) {
        logger.debug("Entering fileProcessing(filePath=" + filePath + ", userId=" + userId + ", type=" + type + ")");
        String ctlFile;
        if("notebooks".equals(type)) {
            ctlFile = "C:/SQLLOADER_RIMS/GOODS.ctl";
        } else if ("employees".equals(type)) {
            ctlFile = "C:/SQLLOADER_RIMS/Users.ctl";
        } else {
            return "! Not valid entity type";
        }

        StringBuilder stringBuilder = new StringBuilder("sqlldr control=")
                .append(ctlFile)
                .append(" userid=")
                .append("kurator2")
                .append("/")
                .append("12345")
                .append(" data=")
                .append(filePath);

        try {
            logger.debug("Executing command " + stringBuilder);
            Runtime.getRuntime().exec(stringBuilder.toString());
            return importDao.processData(userId, type);
        } catch (IOException e){
            logger.error("Error executing command", e);
            return "! Error executing command";
        }
    }

}
