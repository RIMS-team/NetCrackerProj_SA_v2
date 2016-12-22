package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.UserMapper;
import com.xvitcoder.springmvcangularjs.dao.UserDAO;
import com.xvitcoder.springmvcangularjs.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

/**
 * Created by trvler135 on 06.12.2016.
 */
public class JdbcUser implements UserDAO {

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public List<User> findAll() {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        List<User> users;
        try {
            String sql = "SELECT USR.OBJECT_ID AS EMPLOYEE_ID, PHONE_ATTR.VALUE AS PHONE_NUMBER, FNAME_ATTR.VALUE AS FULL_NAME, EMAIL_ATTR.VALUE AS EMAIL, PASS_ATTR.VALUE as PASSWORD " +
                    "FROM OBJECTS USR, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR, ATTRIBUTES PASS_ATTR " +
                    "WHERE USR.OBJECT_TYPE_ID = 2 " +
                    "AND USR.OBJECT_ID = FNAME_ATTR.OBJECT_ID " +
                    "AND USR.OBJECT_ID = EMAIL_ATTR.OBJECT_ID " +
                    "AND USR.OBJECT_ID = PHONE_ATTR.OBJECT_ID " +
                    "AND USR.OBJECT_ID = PASS_ATTR.OBJECT_ID " +
                    "AND FNAME_ATTR.ATTR_ID = 1 " +
                    "AND EMAIL_ATTR.ATTR_ID = 2 " +
                    "AND PHONE_ATTR.ATTR_ID = 3 " +
                    "AND PASS_ATTR.ATTR_ID = 4";
            users = jdbcTemplateObject.query(sql, new UserMapper());
        }catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        System.out.println("DAO");
        for (User x : users){
            System.out.println(x.getFullName());
        }
        return users;
    }

    @Override
    public User findByEmail(String email) {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        User user;
        try {
            String sql = "SELECT USR.OBJECT_ID AS EMPLOYEE_ID, PHONE_ATTR.VALUE AS PHONE_NUMBER, FNAME_ATTR.VALUE AS FULL_NAME, EMAIL_ATTR.VALUE AS EMAIL, PASS_ATTR.VALUE as PASSWORD\n" +
                    "                    FROM OBJECTS USR, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR, ATTRIBUTES PASS_ATTR\n" +
                    "                    WHERE USR.OBJECT_TYPE_ID = 2 /* USER */\n" +
                    "                    AND USR.OBJECT_ID = FNAME_ATTR.OBJECT_ID\n" +
                    "                    AND USR.OBJECT_ID = EMAIL_ATTR.OBJECT_ID\n" +
                    "                    AND USR.OBJECT_ID = PHONE_ATTR.OBJECT_ID\n" +
                    "                    AND USR.OBJECT_ID = PASS_ATTR.OBJECT_ID\n" +
                    "                    AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */\n" +
                    "                    AND EMAIL_ATTR.ATTR_ID = 2 /* EMAIL */\n" +
                    "                    AND PHONE_ATTR.ATTR_ID = 3 /* PHONE_NUMBER */\n" +
                    "                    AND PASS_ATTR.ATTR_ID = 4/* PASSWORD*/\n" +
                    "                    AND EMAIL_ATTR.VALUE = ?";
            user = jdbcTemplateObject.queryForObject(sql, new Object[]{email}, new UserMapper());
        }catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return user;
    }
}
