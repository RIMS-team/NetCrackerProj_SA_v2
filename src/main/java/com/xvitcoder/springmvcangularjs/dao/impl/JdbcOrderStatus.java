package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderStatusMapper;
import com.xvitcoder.springmvcangularjs.dao.OrderStatusDao;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
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
 * Created by dell on 08-Dec-16.
 */
public class JdbcOrderStatus implements OrderStatusDao {

    private Logger logger = Logger.getLogger(JdbcOrderStatus.class);

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
    public List<OrderStatus> findAll() {
        logger.debug("Entering findAll()");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        List<OrderStatus> orderStatusList;
        try {
            String sql =
                "SELECT T.ID, T.CODE, T.NAME " +
                "FROM LISTTYPE T " +
                " WHERE T.ATTRTYPE_CODE = 'ORD_STATUS' ";

            orderStatusList = jdbcTemplateObject.query(sql, new OrderStatusMapper());
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
//        System.out.println("DAO - Order Status");
//        for (OrderStatus x : orderStatusList){
//            System.out.println(x.toString());
//        }
        logger.debug("Leaving findAll():" + orderStatusList);
        return orderStatusList;
    }

    @Override
    public OrderStatus findById(int id) {
        logger.debug("Entering findById(id=" + id + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        OrderStatus orderStatus;
        try {
            String sql =
                    "select t.id, t.code, t.name " +
                    "from listtype t " +
                    " where t.attrtype_code = 'ORD_STATUS' " +
                    "and t.id = ? " ;
            orderStatus = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new OrderStatusMapper());
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findById():" + orderStatus);
        return orderStatus;
    }

    @Override
    public void addStatus(OrderStatus orderStatus) {
        logger.debug("Entering addStatus(" + orderStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            String sql = "INSERT INTO LISTTYPE(CODE,NAME,ATTRTYPE_CODE) VALUES (?,?,'ORD_STATUS')";
            jdbcTemplateObject.update(sql,orderStatus.getCode(),orderStatus.getName());
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            logger.error("Error inserting OrderStatus, rolling back", ex);
            transactionManager.rollback(status);
            throw ex;
        }
        logger.debug("Leaving addStatus(" + orderStatus + ")");
    }

    @Override
    public void updateStatus(OrderStatus orderStatus) {
        logger.debug("Entering updateStatus(" + orderStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            String sql = "UPDATE LISTTYPE SET CODE = ?, NAME = ? WHERE ATTRTYPE_CODE = 'ORD_STATUS' AND ID = ?";
            jdbcTemplateObject.update(sql,orderStatus.getCode(),orderStatus.getName(),orderStatus.getId());
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            logger.debug("Error updating OrderStatus, rolling back", ex);
            transactionManager.rollback(status);
            throw ex;
        }
        logger.debug("Leaving updateStatus(" + orderStatus + ")");
    }

}
