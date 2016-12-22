package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.NotebookMapper;
import com.xvitcoder.springmvcangularjs.dao.NotebookDAO;
import com.xvitcoder.springmvcangularjs.model.Notebook;
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

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public List<Notebook> findAll() {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        List<Notebook> notebooks;
        try {
            String sql =
                    "SELECT NOTE.OBJECT_ID NOTE_ID, NAME_ATTR.VALUE NAME, LOC_ATTR.VALUE LOCATION, " +
                    "MEM_ATTR.VALUE MEM_TYPE, MODEL_ATTR.VALUE MODEL, AFN_ATTR.VALUE A_F_N, SER_ATTR.VALUE SERIAL_NUMBER, " +
                    "STATUS_ATTR.VALUE STATUS " +
                    "FROM OBJECTS NOTE, ATTRIBUTES NAME_ATTR, ATTRIBUTES LOC_ATTR, ATTRIBUTES MEM_ATTR, " +
                    "ATTRIBUTES MODEL_ATTR, ATTRIBUTES AFN_ATTR, ATTRIBUTES SER_ATTR, ATTRIBUTES STATUS_ATTR " +
                    "WHERE NOTE.OBJECT_TYPE_ID = 7 " +
                    "AND NOTE.OBJECT_ID = NAME_ATTR.OBJECT_ID " +
                    "AND NOTE.OBJECT_ID = LOC_ATTR.OBJECT_ID " +
                    "AND NOTE.OBJECT_ID = MEM_ATTR.OBJECT_ID " +
                    "AND NOTE.OBJECT_ID = MODEL_ATTR.OBJECT_ID " +
                    "AND NOTE.OBJECT_ID = AFN_ATTR.OBJECT_ID " +
                    "AND NOTE.OBJECT_ID = SER_ATTR.OBJECT_ID " +
                    "AND NOTE.OBJECT_ID = STATUS_ATTR.OBJECT_ID " +
                    "AND NAME_ATTR.ATTR_ID = 9 " +
                    "AND LOC_ATTR.ATTR_ID = 10 " +
                    "AND MEM_ATTR.ATTR_ID = 11 " +
                    "AND MODEL_ATTR.ATTR_ID = 12 " +
                    "AND AFN_ATTR.ATTR_ID = 13 " +
                    "AND SER_ATTR.ATTR_ID = 14 " +
                    "AND STATUS_ATTR.ATTR_ID = 16";

            notebooks = jdbcTemplateObject.query(sql, new NotebookMapper());
        }
        catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }

        System.out.println("DAO");
        for (Notebook x : notebooks){
            System.out.println(x.getName());
        }
        return notebooks;
    }
}
