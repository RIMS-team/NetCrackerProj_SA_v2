package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.NotebookMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.NotificationMapper;
import com.xvitcoder.springmvcangularjs.dao.NotificationDao;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.Notification;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

/**
 * Created by Admin on 18.01.2017.
 */
public class JdbcNotificationDao implements NotificationDao {
    private Logger logger = Logger.getLogger(JdbcNotebook.class);


    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
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
}
