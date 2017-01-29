package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.InventStatusDao;
import com.xvitcoder.springmvcangularjs.dao.Mappers.InventStatusMapper;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.InventStatus;
import org.apache.log4j.Logger;
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
public class JdbcInventStatus implements InventStatusDao {

    private Logger logger = Logger.getLogger(JdbcInventStatus.class);

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
    public List<InventStatus> findAll() {
        logger.debug("Entering findAll()");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        List<InventStatus> inventStatusList;
        try {
            String sql =
                    "SELECT T.ID, T.CODE, T.NAME " +
                            "FROM LISTTYPE T " +
                            " WHERE T.ATTRTYPE_CODE = 'INV_STATUS' ";

            inventStatusList = jdbcTemplateObject.query(sql, new InventStatusMapper());
            transactionManager.commit(status);
        }catch (DataAccessException e) {
            logger.error("Error in select status records, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
//        System.out.println("DAO - Inventory Status");
//        for (InventStatus x : inventStatusList){
//            System.out.println(x.toString());
//        }
        logger.debug("Leaving findAll():" + inventStatusList);
        return inventStatusList;
    }

    @Override
    public InventStatus findById(int id) {
        logger.debug("Entering findById(id=" + id + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        InventStatus inventStatus;
        try {
            String sql =
                    "select t.id, t.code, t.name " +
                            "from listtype t " +
                            " where t.attrtype_code = 'INV_STATUS' " +
                            "and t.id = ? " ;
            inventStatus = jdbcTemplateObject.queryForObject(sql, new Object[]{id}, new InventStatusMapper());
            transactionManager.commit(status);
        }catch (DataAccessException e) {
            logger.error("Error in select status record, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findById():" + inventStatus);
        return inventStatus;
    }

    @Override
    public ErrorText addStatus(InventStatus inventStatus) {
        logger.debug("Entering addStatus(" + inventStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        SimpleJdbcCall simpleJdbcCall;
        Map map1;
        int errCode;
        String errMsg;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_inv_status")
                    .withProcedureName("inv_status_insert_ext")
                    .declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR))
                    .declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_id",null);
            map.put("p_code",inventStatus.getCode());
            map.put("p_name", inventStatus.getName());
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
            logger.error("Error inserting inventStatus", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

    @Override
    public ErrorText updateStatus(InventStatus inventStatus) {
        logger.debug("Entering updateStatus(" + inventStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        SimpleJdbcCall simpleJdbcCall;
        Map map1;
        int errCode;
        String errMsg;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_inv_status")
                    .withProcedureName("inv_status_update_ext")
                    .declareParameters(new SqlOutParameter("p_err_code", Types.VARCHAR))
                    .declareParameters(new SqlOutParameter("p_err_msg", Types.VARCHAR));
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_id",inventStatus.getId());
            map.put("p_code",inventStatus.getCode());
            map.put("p_name", inventStatus.getName());
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
            logger.error("Error updating inventStatus", e);
            transactionManager.rollback(status);
            throw e;
        }
        return new ErrorText(errCode, errMsg);
    }

}
