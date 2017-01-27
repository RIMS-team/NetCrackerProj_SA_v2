package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.Mappers.MailInformationMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.NotificationTempMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderMapper;
import com.xvitcoder.springmvcangularjs.dao.NotificationTempDao;
import com.xvitcoder.springmvcangularjs.model.MailInformation;
import com.xvitcoder.springmvcangularjs.model.Notification;
import com.xvitcoder.springmvcangularjs.model.NotificationTemp;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import com.xvitcoder.springmvcangularjs.service.NotificationService;
import com.xvitcoder.springmvcangularjs.service.impl.NotificationServiceImpl;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Admin on 20.01.2017.
 */
public class JdbcNotificationTemp implements NotificationTempDao {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
        NotificationTempDao notificationService=(NotificationTempDao) context.getBean("notificationTempDAO");
        //notificationService.registerNotifi();
        NotificationTemp list=notificationService.findById(1,0);

            System.out.println(list.toString());

    }
    private Logger logger = Logger.getLogger(JdbcOrder.class);

    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    private SimpleJdbcCall notificationTempCall;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
        notificationTempCall = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        Locale.setDefault(Locale.ENGLISH);
        this.transactionManager = transactionManager;
    }



    @Override
    public NotificationTemp findById(int notifi_num, int user_id) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        notificationTempCall.withCatalogName("dm_notif_templ");
        notificationTempCall.withFunctionName("notif_templ_get");
        logger.debug("Entering findAll()");
        String notificationTemp;
        NotificationTemp notificationTemp1;
        try {

            SqlParameterSource in = new MapSqlParameterSource().addValue("p_notif_num", notifi_num).addValue("p_user_id", null);
            notificationTemp=notificationTempCall.executeFunction(String.class,in);
            notificationTemp1=new NotificationTemp(notifi_num,user_id,notificationTemp);
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error inserting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        return notificationTemp1;
    }

    @Override
    public void insertNotifiTemp(NotificationTemp notificationTemp) {
        logger.debug("Entering insert(notificationTemp=" + notificationTemp + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        SimpleJdbcCall simpleJdbcCall;
        try {
            simpleJdbcCall=new SimpleJdbcCall(jdbcTemplateObject).withCatalogName("dm_notif_templ").withProcedureName("notif_templ_set");
            Map<String ,Object> map=new HashMap<String ,Object>();
            map.put("p_notif_num",notificationTemp.getNotif_num());
            map.put("p_user_id",notificationTemp.getUser_id()==0?null:notificationTemp.getUser_id());
            map.put("p_template",notificationTemp.getTemplate());
            simpleJdbcCall.execute(map);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error inserting access notificationTemp", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public void updateNotifiTemp(int notifi_num, int user_id) {

    }

    @Override
    public void deleteNotifiTemp(int notifi_num, int user_id) {

    }

    @Override
    public List<MailInformation> getCursor(int day_1, int day_2, int day_3) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        notificationTempCall.withCatalogName("dm_notification");
        notificationTempCall.withProcedureName("debtors_info_select");
        notificationTempCall.returningResultSet("P_OUT_CURSOR", new MailInformationMapper());

        Map<String, Object> args = new HashMap<>(2);
        Map<String, Object> result = new HashMap<>(1);
        List<MailInformation> orders;
        try {
            args.put("p_notif_1_shift_days",day_1);
            args.put("p_notif_2_shift_days",day_2);
            args.put("p_notif_3_shift_days",day_3);
            result.put("P_OUT_CURSOR", "");
            result = notificationTempCall.execute(args);
            orders = (List<MailInformation>) result.get("P_OUT_CURSOR");
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error inserting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + orders);
        return orders;
    }

    @Override
    public void updateStatus() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        notificationTempCall.withCatalogName("dm_notification");
        notificationTempCall.withProcedureName("recalc_orders_statuses");
        try {
        notificationTempCall.execute();
            System.out.println(90);
        transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error updating, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public void registerNotifi(String orderid, int day_1, int day_2, int day_3) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        notificationTempCall.withCatalogName("dm_notification");
        notificationTempCall.withProcedureName("register_notifications");
        Map<String, Object> args = new HashMap<>(3);
        try {
            args.put("p_orders_id_comma_delimited",orderid);
            args.put("p_notif_1_shift_days",day_1);
            args.put("p_notif_2_shift_days",day_2);
            args.put("p_notif_3_shift_days",day_3);
            notificationTempCall.execute(args);
            System.out.println("All good!!");
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            logger.error("Error updating, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
    }

    @Override
    public List<NotificationTemp> getAllDefTemp() {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        notificationTempCall.withCatalogName("dm_notif_templ");
        notificationTempCall.withProcedureName("notif_templ_select");
        notificationTempCall.returningResultSet("P_OUT_CURSOR", new NotificationTempMapper());

        Map<String, Object> result = new HashMap<>(1);
        List<NotificationTemp> orders;
        try {
            result.put("P_OUT_CURSOR", "");
            result = notificationTempCall.execute();
            orders = (List<NotificationTemp>) result.get("P_OUT_CURSOR");
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            logger.error("Error inserting user, rolling back", e);
            transactionManager.rollback(status);
            throw e;
        }
        logger.debug("Leaving findAll():" + orders);
        return orders;
    }
}
