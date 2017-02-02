package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.ImportDao;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Kristina on 31.01.2017.
 */
public class JdbcImport implements ImportDao {

    private Logger logger = Logger.getLogger(JdbcImport.class);

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    private SimpleJdbcCall processData;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
    }

    @Override
    public String processData(int userId, String type) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        String procedureName;
        if("notebooks".equals(type)) {
            procedureName = "sdb_data_processing";
        } else if ("employees".equals(type)) {
            procedureName = "sdb_emp_data_processing";
        } else {
            return "! Not valid entity type";
        }

        processData = new SimpleJdbcCall(jdbcTemplateObject).withProcedureName(procedureName);
        Map<String, Object> args = new HashMap<>();
        args.put("user_id", userId);
        Map result = processData.execute(args);

        transactionManager.commit(status);
        String logs = (String) result.get("LOGS");
        if(logs == null) {
            logs = "! File contains not valid data";
        }
        return logs;
    }

}
