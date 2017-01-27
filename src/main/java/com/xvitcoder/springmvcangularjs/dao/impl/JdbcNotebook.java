package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.AccessCardMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.NotebookMapper;
import com.xvitcoder.springmvcangularjs.dao.NotebookDAO;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Elina on 08.12.2016.
 */
public class JdbcNotebook implements NotebookDAO {

    private Logger logger = Logger.getLogger(JdbcNotebook.class);

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    private SimpleJdbcCall notebookSelectSP;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;

        notebookSelectSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        notebookSelectSP.withCatalogName("DM_NOTEBOOK");
        notebookSelectSP.withProcedureName("NOTEBOOK_SELECT_EXT");
        notebookSelectSP.returningResultSet("P_OUT_CURSOR", new NotebookMapper());
    }

    @Override
    public List<Notebook> findAll() {
        logger.debug("Entering findAll()");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Map<String, Object> args = new HashMap<>(6);
        Map<String, Object> result = new HashMap<>(1);
        List<Notebook> notebooks;
        try {
            args.put("P_OBJECT_ID", null);
            args.put("P_INV_STATUS_ID", null);
            args.put("P_NAME_MASK",null);
            args.put("P_LOCATION_MASK",null);
            args.put("P_ROWNUM_FIRST", null);
            args.put("P_ROWNUM_LAST", null);
            result.put("P_OUT_CURSOR", "");
            result = notebookSelectSP.execute(args);
            notebooks = (List<Notebook>) result.get("P_OUT_CURSOR");
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + notebooks);
        return notebooks;
    }

}
