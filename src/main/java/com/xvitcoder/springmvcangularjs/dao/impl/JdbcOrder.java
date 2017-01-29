package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderMapper;
import com.xvitcoder.springmvcangularjs.dao.OrderDAO;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.*;

/**
 * Created by Elina on 11.01.2017.
 */
public class JdbcOrder implements OrderDAO {

    private Logger logger = Logger.getLogger(JdbcOrder.class);

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;


    private SimpleJdbcCall orderSelectSP;
    private SimpleJdbcCall orderUpdateSP;
    private SimpleJdbcCall orderInsertSP;
    private SimpleJdbcCall orderDeleteSP;

    private SimpleJdbcCall cardUpdateSP;

//    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
//    private AccessCardService accessCardService = (AccessCardService) context.getBean("accessCardServiceImpl");

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);

        orderSelectSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        orderSelectSP.withCatalogName("DM_ORDER");
        orderSelectSP.withProcedureName("ORDER_SELECT");
        orderSelectSP.returningResultSet("P_OUT_CURSOR", new OrderMapper());

        orderUpdateSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        orderUpdateSP.withCatalogName("DM_ORDER");
        orderUpdateSP.withProcedureName("ORDER_UPDATE_EXT");
        orderUpdateSP.declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR));
        orderUpdateSP.declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));

        orderInsertSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        orderInsertSP.withCatalogName("DM_ORDER");
        orderInsertSP.withProcedureName("ORDER_INSERT_EXT");
        orderInsertSP.declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR));
        orderInsertSP.declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));

        orderDeleteSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        orderDeleteSP.withCatalogName("DM_ORDER");
        orderDeleteSP.withProcedureName("ORDER_DELETE_EXT");
        orderDeleteSP.declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR));
        orderDeleteSP.declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));

        cardUpdateSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        cardUpdateSP.withCatalogName("DM_ACCESS_CARD");
        cardUpdateSP.withProcedureName("ACCESS_CARD_UPDATE_EXT");
        cardUpdateSP.declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR));
        cardUpdateSP.declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
    }

    @Override
    public List<OrderCursor> findAll() {
        logger.debug("Entering JdbcOrder.findAll()");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Map<String, Object> args = new HashMap<>(4);
        Map<String, Object> result = new HashMap<>(1);
        List<OrderCursor> orders;
        try {
            args.put("P_OBJECT_ID", null);
            args.put("P_ORD_STATUS_ID", null);
            args.put("P_ROWNUM_FIRST", null);
            args.put("P_ROWNUM_LAST", null);
            result.put("P_OUT_CURSOR", "");
            result = orderSelectSP.execute(args);
            orders = (List<OrderCursor>) result.get("P_OUT_CURSOR");
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error inserting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving JdbcOrder.findAll():" + orders);
        return orders;
    }

    @Override
    public OrderCursor findById(int id) {
        logger.debug("Entering JdbcOrder.findById(id=" + id + ")");
        logger.warn("Method JdbcOrder.findById() not yet implemented");
        return null;
    }

    @Override
    public ErrorText updateOrder(OrderCursor order) {
        logger.debug("Entering JdbcOrder.updateOrder(order=" + order + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Map<String, Object> args = new HashMap<>(6);
        Map map1;
        int errCode;
        String errMsg;
        try {
            args.put("P_OBJECT_ID", order.getId());
            args.put("P_INVENTORY_ID", order.getInventoryId());
            args.put("P_EMPLOYEE_ID", order.getEmployeeId());
            args.put("P_USER_ID", order.getUserId());
            args.put("P_ORD_STATUS_ID", order.getStatusId());
            args.put("P_DATE", order.getDate());

            map1=orderUpdateSP.execute(args);
            errCode = Integer.valueOf((String)map1.get("p_err_code"));
            errMsg= (String)map1.get("p_err_msg");
            if (errCode != 0) {
                System.out.println((String)map1.get("p_err_msg"));
                System.out.println(Integer.valueOf((String)map1.get("p_err_code")));
                transactionManager.rollback(status);
            } else {
                transactionManager.commit(status);
            }
        }
        catch (DataAccessException e) {
            logger.error("Error updating order, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

    @Override
    public ErrorText addOrder(OrderCursor order) {
        logger.debug("Entering JdbcOrder.insertOrder(order=" + order + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Map<String, Object> args = new HashMap<>(6);
        Map map1;
        int errCode;
        String errMsg;
        try {
            args.put("P_OBJECT_ID", null);
            args.put("P_INVENTORY_ID", order.getInventoryId());
            args.put("P_EMPLOYEE_ID", order.getEmployeeId());
            args.put("P_USER_ID", order.getUserId());
            args.put("P_ORD_STATUS_ID", order.getStatusId());
            args.put("P_DATE", order.getDate());

            orderInsertSP.execute(args);
            map1=orderInsertSP.execute(args);
            errCode = Integer.valueOf((String)map1.get("p_err_code"));
            errMsg= (String)map1.get("p_err_msg");
            if (errCode != 0) {
                System.out.println((String)map1.get("p_err_msg"));
                System.out.println(Integer.valueOf((String)map1.get("p_err_code")));
                transactionManager.rollback(status);
            } else {
                args.put("P_OBJECT_ID", order.getInventoryId());
                args.put("P_INVENTORY_NUM", order.getInventoryNum());
                args.put("P_INV_STATUS_ID", 1); // IN_USE (Используется)

                map1=cardUpdateSP.execute(args);
                errCode = Integer.valueOf((String)map1.get("p_err_code"));
                errMsg= (String)map1.get("p_err_msg");
                if (errCode != 0) {
                    System.out.println((String)map1.get("p_err_msg"));
                    System.out.println(Integer.valueOf((String)map1.get("p_err_code")));
                    transactionManager.rollback(status);
                } else {
                    transactionManager.commit(status);
                }
            }
//            accessCardService.updateCard(new AccessCard
//                    (order.getInventoryId(), 1, "Используется", order.getInventoryNum()));
        }
        catch (DataAccessException e) {
            logger.error("Error adding new order, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

    @Override
    public ErrorText deleteOrder(int id) {
        logger.debug("Entering JdbcOrder.deleteOrder(orderId=" + id + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Map<String, Object> args = new HashMap<>(1);
        Map map1;
        int errCode;
        String errMsg;
        try {
            args.put("P_OBJECT_ID", id);
            map1=orderDeleteSP.execute(args);
            errCode = Integer.valueOf((String)map1.get("p_err_code"));
            errMsg= (String)map1.get("p_err_msg");
            if (errCode != 0) {
                System.out.println((String)map1.get("p_err_msg"));
                System.out.println(Integer.valueOf((String)map1.get("p_err_code")));
                transactionManager.rollback(status);
            } else {
                transactionManager.commit(status);
            }
        }
        catch (DataAccessException e) {
            logger.error("Error deleting order, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

}
