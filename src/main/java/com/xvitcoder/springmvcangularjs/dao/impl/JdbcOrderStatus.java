package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderStatusMapper;
import com.xvitcoder.springmvcangularjs.dao.OrderStatusDao;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
    public ErrorText addStatus(OrderStatus orderStatus) {
        logger.debug("Entering addStatus(" + orderStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        SimpleJdbcCall simpleJdbcCall;
        Map map1;
        int errCode;
        String errMsg;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_ord_status")
                    .withProcedureName("ord_status_insert_ext")
                    .declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR))
                    .declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_id",null);
            map.put("p_code",orderStatus.getCode());
            map.put("p_name", orderStatus.getName());
            map.put("p_comments", null);
            map1=simpleJdbcCall.execute(map);
            errCode = Integer.valueOf((String)map1.get("p_err_code"));
            errMsg= (String)map1.get("p_err_msg");
            if (errCode != 0) {
                System.out.println((String)map1.get("p_err_msg"));
                System.out.println(Integer.valueOf((String)map1.get("p_err_code")));
                transactionManager.rollback(status);
            } else {
                transactionManager.commit(status);
            }
        } catch (DataAccessException e) {
            logger.error("Error inserting ordStatus", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

    @Override
    public ErrorText updateStatus(OrderStatus orderStatus) {
        logger.debug("Entering updateStatus(" + orderStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        SimpleJdbcCall simpleJdbcCall;
        Map map1;
        int errCode;
        String errMsg;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_ord_status")
                    .withProcedureName("ord_status_update_ext")
                    .declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR))
                    .declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_id",orderStatus.getId());
            map.put("p_code",orderStatus.getCode());
            map.put("p_name", orderStatus.getName());
            map1=simpleJdbcCall.execute(map);
            errCode = Integer.valueOf((String)map1.get("p_err_code"));
            errMsg= (String)map1.get("p_err_msg");
            if (errCode != 0) {
                System.out.println((String)map1.get("p_err_msg"));
                System.out.println(Integer.valueOf((String)map1.get("p_err_code")));
                transactionManager.rollback(status);
            } else {
                transactionManager.commit(status);
            }
        } catch (DataAccessException e) {
            logger.error("Error updating ordStatus", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

}
