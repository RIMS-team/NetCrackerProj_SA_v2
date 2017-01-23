package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.service.ImportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Kristina on 17.01.2017.
 */

public class CSVImportService implements ImportService {

    private Logger logger = Logger.getLogger(CSVImportService.class);

    private JdbcTemplate jdbcTemplateObject;
    private SimpleJdbcCall processData;
    private PlatformTransactionManager transactionManager;
    private TransactionStatus status;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
        this.status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    public void fileProcessing(String filePath, String type) {
        logger.debug("Entering fileProcessing()");
        String ctlFile;
        String procedureName;
        if("notebooks".equals(type)) {
            ctlFile = "C:/SQLLOADER_RIMS/GOODS.ctl";
            procedureName = "sdb_data_processing";
        } else if ("employees".equals(type)) {
            ctlFile = "C:/SQLLOADER_RIMS/Users.ctl";
            procedureName = "sdb_emp_data_processing";
        } else {
            return;
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
            try {
                logger.info("entered try with procedure call");
                processData = new SimpleJdbcCall(jdbcTemplateObject).withProcedureName(procedureName);
                Map<String, Object> args = new HashMap<>();
                args.put("user_id", "1");
                processData.execute(args);
            } catch (DataAccessException e) {
                logger.error("Error processing data", e);
                throw e;
            }
            logger.debug("Leaving fileProcessing()");
        } catch (IOException e){
            logger.error("Error executing command", e);
        }
    }

}
