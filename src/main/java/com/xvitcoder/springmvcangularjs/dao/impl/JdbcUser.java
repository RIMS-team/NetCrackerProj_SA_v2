package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.AdminMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.UserMapper;
import com.xvitcoder.springmvcangularjs.dao.UserDAO;
import com.xvitcoder.springmvcangularjs.model.Admin;
import com.xvitcoder.springmvcangularjs.model.User;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by trvler135 on 06.12.2016.
 */
public class JdbcUser implements UserDAO {

    private Logger logger = Logger.getLogger(JdbcUser.class);

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;
    private SimpleJdbcCall insertUser;
    private SimpleJdbcCall deleteUser;
    private SimpleJdbcCall updateUser;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        insertUser = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_user").withProcedureName("user_insert");
        updateUser = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_user").withProcedureName("user_update");
        deleteUser = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_user").withProcedureName("user_delete");
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
    }

    @Override
    public List<User> findAll() {
        logger.debug("Entering findAll()");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        List<User> users;
        try {
            String sql = "SELECT USR.OBJECT_ID AS EMPLOYEE_ID, PHONE_ATTR.VALUE AS PHONE_NUMBER, FNAME_ATTR.VALUE AS FULL_NAME, EMAIL_ATTR.VALUE AS EMAIL, PASS_ATTR.VALUE as PASSWORD " +
                    "FROM OBJECTS USR, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR, ATTRIBUTES PASS_ATTR " +
                    "WHERE (USR.OBJECT_TYPE_ID = 2 OR USR.OBJECT_TYPE_ID = 3) " + // user or admin
                    "AND USR.OBJECT_ID = FNAME_ATTR.OBJECT_ID " +
                    "AND USR.OBJECT_ID = EMAIL_ATTR.OBJECT_ID " +
                    "AND USR.OBJECT_ID = PHONE_ATTR.OBJECT_ID " +
                    "AND USR.OBJECT_ID = PASS_ATTR.OBJECT_ID " +
                    "AND FNAME_ATTR.ATTR_ID = 1 " +
                    "AND EMAIL_ATTR.ATTR_ID = 2 " +
                    "AND PHONE_ATTR.ATTR_ID = 3 " +
                    "AND PASS_ATTR.ATTR_ID = 4";
            users = jdbcTemplateObject.query(sql, new UserMapper());
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error in select user records, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + users);
        return users;
    }


    @Override
    public Admin findByEmail(String email) {
        logger.debug("Entering findByEmail(email=" + email + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Admin user;
        try {
            String sql = "SELECT USR.OBJECT_ID AS EMPLOYEE_ID, PHONE_ATTR.VALUE AS PHONE_NUMBER, FNAME_ATTR.VALUE AS FULL_NAME, EMAIL_ATTR.VALUE AS EMAIL, PASS_ATTR.VALUE as PASSWORD, USR.OBJECT_TYPE_ID as ROLE\n" +
                    "                    FROM OBJECTS USR, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR, ATTRIBUTES PASS_ATTR\n" +
                    "                    WHERE (USR.OBJECT_TYPE_ID = 2 OR USR.OBJECT_TYPE_ID = 3)/* USER OR ADMIN */\n" +
                    "                    AND USR.OBJECT_ID = FNAME_ATTR.OBJECT_ID\n" +
                    "                    AND USR.OBJECT_ID = EMAIL_ATTR.OBJECT_ID\n" +
                    "                    AND USR.OBJECT_ID = PHONE_ATTR.OBJECT_ID\n" +
                    "                    AND USR.OBJECT_ID = PASS_ATTR.OBJECT_ID\n" +
                    "                    AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */\n" +
                    "                    AND EMAIL_ATTR.ATTR_ID = 2 /* EMAIL */\n" +
                    "                    AND PHONE_ATTR.ATTR_ID = 3 /* PHONE_NUMBER */\n" +
                    "                    AND PASS_ATTR.ATTR_ID = 4/* PASSWORD*/\n" +
                    "                    AND EMAIL_ATTR.VALUE = ?";
            user = jdbcTemplateObject.queryForObject(sql, new Object[]{email}, new AdminMapper());
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error in select user record, rolling back", e);
            transactionManager.rollback(status);
            return null;
        }
        logger.debug("Leaving findByEmail():" + user);
        return user;
    }

    @Override
    public void addUser(User user) {
        logger.debug("Entering addUser(user=" + user + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Map<String, Object> args = new HashMap<>();
            args.put("p_object_id",null);
            args.put("p_full_name",user.getFullName());
            args.put("p_phone_number",user.getPhoneNumber());
            args.put("p_email",user.geteMail());
            args.put("p_password",user.getPassword());
            insertUser.execute(args);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error inserting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) {
        logger.debug("Entering deleteUser(id=" + id + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Map<String, Object> args = new HashMap<>();
            args.put("p_object_id",id);
            deleteUser.execute(args);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error deleting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public void updateUser(User user) {
        logger.debug("Entering updateUser(user=" + user + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            Map<String, Object> args = new HashMap<>();
            args.put("p_object_id",user.getId());
            args.put("p_full_name",user.getFullName());
            args.put("p_phone_number",user.getPhoneNumber());
            args.put("p_email",user.geteMail());
            args.put("p_password",user.getPassword());
            updateUser.execute(args);
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            logger.error("Error updating user, rolling back", ex);
            transactionManager.rollback(status);
        }
    }

}
