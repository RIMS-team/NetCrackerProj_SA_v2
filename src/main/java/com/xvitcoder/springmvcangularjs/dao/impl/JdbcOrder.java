package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderMapper;
import com.xvitcoder.springmvcangularjs.dao.OrderDAO;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by Elina on 11.01.2017.
 */
public class JdbcOrder implements OrderDAO {
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    private SimpleJdbcCall orderSelectSP;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);

        orderSelectSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        orderSelectSP.withCatalogName("DM_ORDER");
        orderSelectSP.withProcedureName("ORDER_SELECT");
        orderSelectSP.returningResultSet("P_OUT_CURSOR", new OrderMapper());
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public List<OrderCursor> findAll() {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        Map<String, Object> args = new HashMap<>(2);
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
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            System.out.println("Error inserting user, rolling back");
            transactionManager.rollback(status);
            throw e;
        }

        return orders;
    }

    @Override
    public OrderCursor findById(int id) {
        return null;
    }
}
