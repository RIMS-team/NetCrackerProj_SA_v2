package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderStatusMapper;
import com.xvitcoder.springmvcangularjs.dao.OrderStatusDao;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.apache.log4j.Logger;
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
    private TransactionStatus status;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
        this.status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @Override
    public List<OrderStatus> findAll() {
        logger.debug("Entering findAll()");
        List<OrderStatus> orderStatusList;
        try {
            String sql =
                "SELECT T.ID, T.CODE, T.NAME " +
                "FROM LISTTYPE T " +
                " WHERE T.ATTRTYPE_CODE = 'ORD_STATUS' ";

            orderStatusList = jdbcTemplateObject.query(sql, new OrderStatusMapper());
        } catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
//            transactionManager.rollback(status);
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
        OrderStatus orderStatus;
        try {
            String sql =
                    "select t.id, t.code, t.name " +
                    "from listtype t " +
                    " where t.attrtype_code = 'ORD_STATUS' " +
                    "and t.id = ? " ;
            orderStatus = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new OrderStatusMapper());
        } catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
//            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findById():" + orderStatus);
        return orderStatus;
    }

}
