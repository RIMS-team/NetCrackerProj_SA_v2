package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.NotebookMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.NotificationMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.NotificationTempMapper;
import com.xvitcoder.springmvcangularjs.dao.NotificationDao;
import com.xvitcoder.springmvcangularjs.dao.NotificationTempDao;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.model.NotificationTemp;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Admin on 18.01.2017.
 */
public class JdbcNotificationDao implements NotificationDao {
    private Logger logger = Logger.getLogger(JdbcNotebook.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        NotificationDao notificationService=(NotificationDao) context.getBean("notificationDAO");
        //notificationService.registerNotifi();
        List<Notification> list=notificationService.findAllNotifi();
        for(Notification mailInformation:list) {
            System.out.println(mailInformation.toString());
        }
    }

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;
    private SimpleJdbcCall notificationCall;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        notificationCall = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
    }
    @Override
    public List<Notification> findAll() {
        logger.debug("Entering findAll()");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        List<Notification> notification;
        try {
            String sql =
                    "SELECT /*+ first_rows(30) */\n" +
                            "             row_number() over (order by NOTIF.OBJECT_ID) rn,\n" +
                            "             NOTIF.OBJECT_ID AS NOTIFICATION_ID,\n" +
                            "             ORD_REF.REFERENCE AS ORDER_ID,\n" +
                            "             ATTR_DATE_1.DATE_VALUE AS FIRST_DATE,\n" +
                            "             ATTR_DATE_2.DATE_VALUE AS SECOND_DATE,\n" +
                            "             ATTR_DATE_3.DATE_VALUE AS THIRD_DATE\n" +
                            "          FROM OBJECTS NOTIF\n" +
                            "          JOIN OBJREFERENCE ORD_REF ON ORD_REF.OBJECT_ID = NOTIF.OBJECT_ID AND ORD_REF.ATTR_ID = 25 /*ORDER*/\n" +
                            "          \n" +
                            "          LEFT JOIN ATTRIBUTES ATTR_DATE_1 ON NOTIF.OBJECT_ID = ATTR_DATE_1.OBJECT_ID AND ATTR_DATE_1.ATTR_ID = 20 /* FIRST_DATE */\n" +
                            "          LEFT JOIN ATTRIBUTES ATTR_DATE_2 ON NOTIF.OBJECT_ID = ATTR_DATE_2.OBJECT_ID AND ATTR_DATE_2.ATTR_ID = 21 /* SECOND_DATE */\n" +
                            "          LEFT JOIN ATTRIBUTES ATTR_DATE_3 ON NOTIF.OBJECT_ID = ATTR_DATE_3.OBJECT_ID AND ATTR_DATE_3.ATTR_ID = 22 /* THIRD_DATE */\n" +
                            "          \n" +
                            "         WHERE NOTIF.OBJECT_TYPE_ID = 8 ";
            notification = jdbcTemplateObject.query(sql, new NotificationMapper());
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + notification);
        return notification;
    }

    @Override
    public List<Notification> findAllNotifi() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        notificationCall.withCatalogName("dm_notification");
        notificationCall.withProcedureName("notification_select");
        notificationCall.returningResultSet("P_OUT_CURSOR", new NotificationMapper());

        Map<String, Object> args = new HashMap<>(3);
        Map<String, Object> result = new HashMap<>(1);
        List<Notification> orders;
        try {
            args.put("P_OBJECT_ID", null);
            args.put("P_ROWNUM_FIRST", null);
            args.put("P_ROWNUM_LAST", null);
            result.put("P_OUT_CURSOR", "");
            result = notificationCall.execute(args);
            orders = (List<Notification>) result.get("P_OUT_CURSOR");
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error inserting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + orders);
        return orders;
    }


}
