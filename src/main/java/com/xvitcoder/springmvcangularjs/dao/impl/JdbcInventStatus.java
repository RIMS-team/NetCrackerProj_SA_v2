package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.InventStatusDao;
import com.xvitcoder.springmvcangularjs.dao.Mappers.InventStatusMapper;
import com.xvitcoder.springmvcangularjs.model.InventStatus;
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
    public void addStatus(InventStatus inventStatus) {
        logger.debug("Entering addStatus(" + inventStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            String sql = "INSERT INTO LISTTYPE(CODE,NAME,ATTRTYPE_CODE) VALUES (?,?,'INV_STATUS')";
            jdbcTemplateObject.update(sql,inventStatus.getCode(),inventStatus.getName());
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            logger.error("Error inserting inventStatus, rolling back", ex);
            transactionManager.rollback(status);
            throw ex;
        }
        logger.debug("Leaving addStatus(" + inventStatus + ")");
    }

    @Override
    public void updateStatus(InventStatus inventStatus) {
        logger.debug("Entering updateStatus(" + inventStatus + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            String sql = "UPDATE LISTTYPE SET CODE = ?, NAME = ? WHERE ATTRTYPE_CODE = 'INV_STATUS' AND ID = ?";
            jdbcTemplateObject.update(sql,inventStatus.getCode(),inventStatus.getName(),inventStatus.getId());
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            logger.debug("Error updating inventStatus, rolling back", ex);
            transactionManager.rollback(status);
            throw ex;
        }
        logger.debug("Leaving updateStatus(" + inventStatus + ")");
    }

}
