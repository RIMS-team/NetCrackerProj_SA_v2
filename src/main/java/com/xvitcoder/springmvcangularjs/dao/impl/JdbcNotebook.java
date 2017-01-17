package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.NotebookMapper;
import com.xvitcoder.springmvcangularjs.dao.NotebookDAO;
import com.xvitcoder.springmvcangularjs.model.Notebook;
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
 * Created by Elina on 08.12.2016.
 */
public class JdbcNotebook implements NotebookDAO {

    private Logger logger = Logger.getLogger(JdbcNotebook.class);


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
    public List<Notebook> findAll() {
        logger.debug("Entering findAll()");
        List<Notebook> notebooks;
        try {
            String sql =
                    "SELECT NOTE.OBJECT_ID NOTE_ID, " +
                            "NAME_ATTR.VALUE NAME, " +
                            "LOC_ATTR.VALUE LOCATION, " +
                            "MEM_ATTR.VALUE MEM_TYPE, " +
                            "MODEL_ATTR.VALUE MODEL, " +
                            "INVENTORY_NUM_ATTR.VALUE INVENTORY_NUM, " +
                            "SER_ATTR.VALUE SERIAL_NUMBER " +
                            ",STATUS_ATTR.VALUE  AS INV_STATUS_ID " +
                            ",LIST_STATUS.NAME   AS INV_STATUS_NAME " +
                            "FROM OBJECTS NOTE, ATTRIBUTES NAME_ATTR, ATTRIBUTES LOC_ATTR, ATTRIBUTES MEM_ATTR, " +
                            "ATTRIBUTES MODEL_ATTR, ATTRIBUTES INVENTORY_NUM_ATTR, ATTRIBUTES SER_ATTR, ATTRIBUTES STATUS_ATTR, LISTTYPE LIST_STATUS " +
                            "WHERE NOTE.OBJECT_TYPE_ID = 7 " +
                            "AND NOTE.OBJECT_ID = NAME_ATTR.OBJECT_ID " +
                            "AND NOTE.OBJECT_ID = LOC_ATTR.OBJECT_ID " +
                            "AND NOTE.OBJECT_ID = MEM_ATTR.OBJECT_ID " +
                            "AND NOTE.OBJECT_ID = MODEL_ATTR.OBJECT_ID " +
                            "AND NOTE.OBJECT_ID = INVENTORY_NUM_ATTR.OBJECT_ID " +
                            "AND NOTE.OBJECT_ID = SER_ATTR.OBJECT_ID " +
                            "AND NOTE.OBJECT_ID = STATUS_ATTR.OBJECT_ID " +
                            "AND NAME_ATTR.ATTR_ID = 9/*NAME*/ " +
                            "AND LOC_ATTR.ATTR_ID = 10/*LOCATION*/ " +
                            "AND MEM_ATTR.ATTR_ID = 11/*MEMORY_TYPE*/ " +
                            "AND MODEL_ATTR.ATTR_ID = 12/*MODEL*/ " +
                            "AND INVENTORY_NUM_ATTR.ATTR_ID = 13/*INVENTORY_NUM*/ " +
                            "AND SER_ATTR.ATTR_ID = 14/*SERIAL_NUMBER*/ " +
                            "AND STATUS_ATTR.ATTR_ID = 16/*STATUS*/ " +
                            "AND STATUS_ATTR.VALUE = LIST_STATUS.ID ";

            notebooks = jdbcTemplateObject.query(sql, new NotebookMapper());
        }
        catch (DataAccessException e) {
            logger.error("Error in select record, rolling back", e);
//            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + notebooks);
        return notebooks;
    }

}
