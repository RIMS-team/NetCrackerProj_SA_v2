package com.xvitcoder.springmvcangularjs.dao;


import com.xvitcoder.springmvcangularjs.dao.Mappers.AccessCardMapper;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
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
 * Created by Kristina on 05.12.2016.
 */
public class JdbcAccessCardTemplate implements AccessCardDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void insert(AccessCard accessCard) {
//        INSERT ALL
//        INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (29,NULL,6,'CARD_1',NULL)
//        INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,VALUE_ID)VALUES (16,29,NULL,null,2)
//        INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,VALUE_ID)VALUES (17,29,'T22G362I',null,null)
//        SELECT * FROM dual;
    }

    @Override
    public AccessCard findByCardId(int cardId) {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        AccessCard accessCard = null;
        try {
            String sql = "SELECT CARD.OBJECT_ID AS OBJECT_ID, STATUS.NAME AS CARD_STATUS, CARD_ID.VALUE AS CARD_ID\n" +
                    "FROM OBJECTS CARD, ATTRIBUTES ATTR_CARD_ID, ATTRIBUTES ATTR_STATUS, LISTTYPE STATUS\n" +
                    "WHERE CARD.OBJECT_TYPE_ID = 6 /* CARD */\n" +
                    "AND CARD.OBJECT_ID = ATTR_CARD_ID.OBJECT_ID\n" +
                    "AND CARD.OBJECT_ID = ATTR_STATUS.OBJECT_ID\n" +
                    "AND ATTR_CARD_ID.ATTR_ID = 17 /* CARD_ID */\n" +
                    "AND ATTR_STATUS.ATTR_ID = 16 /* INVENTORY_STATUS */\n" +
                    "AND ATTR_STATUS.VALUE = STATUS.ID\n" +
                    "AND CARD.OBJECT_ID = ?";
            accessCard = jdbcTemplateObject.queryForObject(sql,
                    new Object[]{cardId}, new AccessCardMapper());
        } catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return accessCard;
    }

    @Override
    public List<AccessCard> findAllCards() {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String sql = "SELECT CARD.OBJECT_ID AS OBJECT_ID, LIST_STATUS.NAME AS CARD_STATUS, ATTR_CARD_ID.VALUE AS CARD_ID\n" +
                "FROM OBJECTS CARD, ATTRIBUTES ATTR_CARD_ID, ATTRIBUTES ATTR_STATUS, LISTTYPE LIST_STATUS\n" +
                "WHERE CARD.OBJECT_TYPE_ID = 6 /* CARD */\n" +
                "AND CARD.OBJECT_ID = ATTR_CARD_ID.OBJECT_ID\n" +
                "AND CARD.OBJECT_ID = ATTR_STATUS.OBJECT_ID\n" +
                "AND ATTR_CARD_ID.ATTR_ID = 17 /* CARD_ID */\n" +
                "AND ATTR_STATUS.ATTR_ID = 16 /* INVENTORY_STATUS */\n" +
                "AND ATTR_STATUS.VALUE = LIST_STATUS.ID";
        List <AccessCard> accessCards = null;
        try {
            accessCards = jdbcTemplateObject.query(sql,
                    new AccessCardMapper());
        }catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return accessCards;
    }
}
