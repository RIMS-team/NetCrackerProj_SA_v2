package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.MailSettingsDao;
import com.xvitcoder.springmvcangularjs.model.MailSettings;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
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
 * Created by Stas on 29.01.2017.
 */
public class JdbcMailSettings implements MailSettingsDao{

    private Logger logger = Logger.getLogger(JdbcMailSettings.class);

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    private SimpleJdbcCall getMailSettings;
    private SimpleJdbcCall updateMailSettings;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        getMailSettings = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        getMailSettings.withCatalogName("DM_PARAM");
        getMailSettings.withProcedureName("get_parameter");

        updateMailSettings = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        updateMailSettings.withCatalogName("DM_PARAM");
        updateMailSettings.withProcedureName("set_parameter");
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
    }

    @Override
    public MailSettings getMailSettings() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        logger.debug("Entering JdbcMailSettings.getMailSettings()");
         String host;
         String socketFactoryPort;
         String socketFactoryClass;
         String auth;
         String port;
         String from;
        MailSettings mailSettings;
        String res = "P_VALUE";
        String p_group = "p_group";
        String email = "email";
        String p_code = "p_code";

        try {

            SqlParameterSource in = new MapSqlParameterSource().addValue(p_group, email).addValue(p_code, "mail.smtp.host");
            host = (String) getMailSettings.execute(in).get(res);
            in = new MapSqlParameterSource().addValue(p_group, email).addValue(p_code, "mail.smtp.socketFactory.port");
            socketFactoryPort = (String) getMailSettings.execute(in).get(res);
            in = new MapSqlParameterSource().addValue(p_group, email).addValue(p_code, "mail.smtp.socketFactory.class");
            socketFactoryClass = (String) getMailSettings.execute(in).get(res);
            in = new MapSqlParameterSource().addValue(p_group, email).addValue(p_code, "mail.smtp.auth");
            auth = (String) getMailSettings.execute(in).get(res);
            in = new MapSqlParameterSource().addValue(p_group, email).addValue(p_code, "mail.smtp.port");
            port = (String) getMailSettings.execute(in).get(res);
            in = new MapSqlParameterSource().addValue(p_group, email).addValue(p_code, "from");
            from = (String) getMailSettings.execute(in).get(res);

            mailSettings = new MailSettings(host, socketFactoryPort, socketFactoryClass, auth, port, from);
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error getting mail settings, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }

        return mailSettings;
    }

    @Override
    public void updateMailSettings(MailSettings mailSettings) {
        logger.debug("Entering JdbcMailSettings.updateMailSettings(" + mailSettings + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Map<String, Object> map = new HashMap<>(6);
        String p_value = "p_value";
        String p_group = "p_group";
        String email = "email";
        String p_code = "p_code";

        try {
        map.put(p_group, email);
        map.put(p_code, "mail.smtp.host");
        map.put(p_value, mailSettings.getHost());
        updateMailSettings.execute(map);

        map.put(p_group, email);
        map.put(p_code, "mail.smtp.socketFactory.port");
        map.put(p_value, mailSettings.getSocketFactoryPort());
        updateMailSettings.execute(map);

        map.put(p_group, email);
        map.put(p_code, "mail.smtp.socketFactory.class");
        map.put(p_value, mailSettings.getSocketFactoryClass());
        updateMailSettings.execute(map);

        map.put(p_group, email);
        map.put(p_code, "mail.smtp.auth");
        map.put(p_value, mailSettings.getAuth());
        updateMailSettings.execute(map);

        map.put(p_group, email);
        map.put(p_code, "mail.smtp.port");
        map.put(p_value, mailSettings.getPort());
        updateMailSettings.execute(map);

        map.put(p_group, email);
        map.put(p_code, "from");
        map.put(p_value, mailSettings.getFrom());
        updateMailSettings.execute(map);

        transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error updating MailSettings", e);
            transactionManager.rollback(status);
            throw e;
        }
    }
}
