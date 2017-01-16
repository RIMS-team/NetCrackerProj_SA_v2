package com.xvitcoder.springmvcangularjs.dao.impl;

import org.apache.log4j.Logger;
import com.xvitcoder.springmvcangularjs.dao.AccessCardDao;
import com.xvitcoder.springmvcangularjs.dao.Mappers.AccessCardMapper;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
import oracle.jdbc.OracleTypes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
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
 * Created by Kristina on 05.12.2016.
 */
public class JdbcAccessCard implements AccessCardDao {

    private Logger logger = Logger.getLogger(JdbcAccessCard.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;
    private TransactionStatus status;


    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
        this.status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    @Override
    public void insert(AccessCard accessCard) {
        logger.debug("Entering insert(accessCard=" + accessCard + ")");
        SimpleJdbcCall simpleJdbcCall;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_access_card").withProcedureName("access_card_insert");
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_object_id",null);
            map.put("p_inventory_num",accessCard.getInventoryNum());
            map.put("p_inv_status_id", accessCard.getStatusId());
            simpleJdbcCall.execute(map);
        } catch (DataAccessException e) {
            logger.error("Error inserting access card", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public AccessCard findByInventoryNum(int cardId) {
        logger.debug("Entering findByInventoryNum(cardId=" + cardId + ")");
        AccessCard accessCard;
        try {
            String sql =
                    "SELECT CARD.OBJECT_ID AS OBJECT_ID " +
                            ",ATTR_INVENTORY_NUM.VALUE AS INVENTORY_NUM " +
                            ",ATTR_STATUS.VALUE  AS INV_STATUS_ID " +
                            ",LIST_STATUS.NAME   AS INV_STATUS_NAME " +
                            "FROM OBJECTS CARD, ATTRIBUTES ATTR_INVENTORY_NUM, ATTRIBUTES ATTR_STATUS, LISTTYPE LIST_STATUS " +
                            "WHERE CARD.OBJECT_TYPE_ID = 6 /* CARD */ " +
                            "AND CARD.OBJECT_ID = ATTR_INVENTORY_NUM.OBJECT_ID " +
                            "AND CARD.OBJECT_ID = ATTR_STATUS.OBJECT_ID " +
                            "AND ATTR_INVENTORY_NUM.ATTR_ID = 13 /* INVENTORY_NUM */ " +
                            "AND ATTR_STATUS.ATTR_ID = 16 /* STATUS */ " +
                            "AND ATTR_STATUS.VALUE = LIST_STATUS.ID " +
                            "AND ATTR_INVENTORY_NUM.VALUE = ?";
            accessCard = jdbcTemplateObject.queryForObject(sql,
                    new Object[]{cardId}, new AccessCardMapper());
        } catch (DataAccessException e) {
            logger.error("Error finding access card", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findByInventoryNum():" + accessCard);
        return accessCard;
    }

    @Override
    public List<AccessCard> findAll() {
        logger.debug("Entering findAll()");
        String sql =
                "SELECT CARD.OBJECT_ID AS OBJECT_ID " +
                        ",ATTR_INVENTORY_NUM.VALUE AS INVENTORY_NUM " +
                        ",ATTR_STATUS.VALUE  AS INV_STATUS_ID " +
                        ",LIST_STATUS.NAME   AS INV_STATUS_NAME " +
                        "FROM OBJECTS CARD, ATTRIBUTES ATTR_INVENTORY_NUM, ATTRIBUTES ATTR_STATUS, LISTTYPE LIST_STATUS " +
                        "WHERE CARD.OBJECT_TYPE_ID = 6 /* CARD */ " +
                        "AND CARD.OBJECT_ID = ATTR_INVENTORY_NUM.OBJECT_ID " +
                        "AND CARD.OBJECT_ID = ATTR_STATUS.OBJECT_ID " +
                        "AND ATTR_INVENTORY_NUM.ATTR_ID = 13 /* INVENTORY_NUM */ " +
                        "AND ATTR_STATUS.ATTR_ID = 16 /* STATUS */ " +
                        "AND ATTR_STATUS.VALUE = LIST_STATUS.ID ";
        List <AccessCard> accessCards;
        try {
            accessCards = jdbcTemplateObject.query(sql,
                    new AccessCardMapper());
        } catch (DataAccessException e) {
            logger.error("Error finding all access cards, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + accessCards);
        return accessCards;
    }

    @Override
    public void update(AccessCard card) {
        logger.debug("Entering update(card=" + card + ")");
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        SimpleJdbcCall simpleJdbcCall=null;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_access_card").withProcedureName("access_card_update");
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_object_id",card.getId());
            map.put("p_inventory_num",card.getInventoryNum());
            map.put("p_inv_status_id",card.getStatusId());
            simpleJdbcCall.execute(map);

            //System.out.println("2ebhovbhwe bv");
        } catch (DataAccessException e) {
            logger.error("Error updating card, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public void deleteCard(int id) {
        logger.debug("Entering deleteCard(id="+ id + ")");
        SimpleJdbcCall simpleJdbcCall=null;
        try {
            simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_access_card").withProcedureName("access_card_delete");
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_object_id",id);
            simpleJdbcCall.execute(map);
            System.out.println("2ebhovbhwe bv");
        } catch (DataAccessException e) {
            logger.error("Error deleting card, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

}
