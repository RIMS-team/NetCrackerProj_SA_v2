package com.xvitcoder.springmvcangularjs.dao.impl;

import com.xvitcoder.springmvcangularjs.dao.EmployeeDao;
import com.xvitcoder.springmvcangularjs.dao.Mappers.EmployeeMapper;
import com.xvitcoder.springmvcangularjs.dao.Mappers.OrderMiniMapper;
import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.model.OrderCursor;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by netcracker on 29.11.2016.
 */
public class JdbcEmployeeDao implements EmployeeDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    private PlatformTransactionManager transactionManager;

    private Logger logger = Logger.getLogger(JdbcEmployeeDao.class);

    private SimpleJdbcCall orderSelectByEmployeeSP;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);

        orderSelectByEmployeeSP = new SimpleJdbcCall(jdbcTemplateObject.getDataSource());
        orderSelectByEmployeeSP.withCatalogName("DM_ORDER");
        orderSelectByEmployeeSP.withProcedureName("ORDER_SELECT_BY_EMPLOYEE");
        orderSelectByEmployeeSP.returningResultSet("P_OUT_CURSOR", new OrderMiniMapper());
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void insert(Employee employee) {

//        String sql = "INSERT INTO Employee " +
//                "(EMPLOYEE_ID,PHONENUMBER,FULLNAME,EMAIL) VALUES (?, ?, ?, ?)";
        String sql = "INSERT ALL\n" +
                "  INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (28,NULL,1,'Кристина',NULL)\n" +
                "  INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,VALUE_ID)VALUES (1,28,'Кристина',null,null)\n" +
                "  INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,VALUE_ID)VALUES (2,28,'kristina@gmail.com',null,null)\n" +
                "  INTO ATTRIBUTES (ATTR_ID,OBJECT_ID,VALUE,DATE_VALUE,VALUE_ID)VALUES (3,28,'0941232323',null,null)\n" +
                "SELECT * FROM dual;";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, employee.getId());
//            ps.setInt(2, employee.getPhoneNumber());
//            ps.setString(3, employee.getFullName());
//            ps.setString(4, employee.geteMail());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }

    }




    @Override
    public Employee findByEmployeeId(int employeeId) {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);
        Employee employee=null;
        try {
            String sql = "SELECT EMP.OBJECT_ID AS EMPLOYEE_ID, PHONE_ATTR.VALUE AS PHONE_NUMBER, FNAME_ATTR.VALUE AS FULL_NAME, EMAIL_ATTR.VALUE AS EMAIL\n" +
                    "FROM OBJECTS EMP, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR\n" +
                    "WHERE EMP.OBJECT_TYPE_ID = 1 /* EMPLOYEE */\n" +
                    "AND EMP.OBJECT_ID = FNAME_ATTR.OBJECT_ID\n" +
                    "AND EMP.OBJECT_ID = EMAIL_ATTR.OBJECT_ID\n" +
                    "AND EMP.OBJECT_ID = PHONE_ATTR.OBJECT_ID\n" +
                    "AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */\n" +
                    "AND EMAIL_ATTR.ATTR_ID = 2 /* EMAIL */\n" +
                    "AND PHONE_ATTR.ATTR_ID = 3 /* PHONE_NUMBER */\n" +
                    "AND EMP.OBJECT_ID = ?";
            employee = jdbcTemplateObject.queryForObject(sql,
                    new Object[]{employeeId}, new EmployeeMapper());
            transactionManager.commit(status);
        }catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return employee;
    }




    @Override
    public List<Employee> findAllEmployee() {
        Locale.setDefault(Locale.ENGLISH);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String sql=
                "SELECT /*+ first_rows(30) */ " +
                "EMP.OBJECT_ID AS EMPLOYEE_ID, " +
                "PHONE_ATTR.VALUE AS PHONE_NUMBER, " +
                "FNAME_ATTR.VALUE AS FULL_NAME, " +
                "EMAIL_ATTR.VALUE AS EMAIL " +
                "FROM OBJECTS EMP, ATTRIBUTES FNAME_ATTR, ATTRIBUTES EMAIL_ATTR, ATTRIBUTES PHONE_ATTR " +
                "WHERE EMP.OBJECT_TYPE_ID = 1 /* EMPLOYEE */ " +
                "AND EMP.OBJECT_ID = FNAME_ATTR.OBJECT_ID " +
                "AND EMP.OBJECT_ID = EMAIL_ATTR.OBJECT_ID " +
                "AND EMP.OBJECT_ID = PHONE_ATTR.OBJECT_ID " +
                "AND FNAME_ATTR.ATTR_ID = 1 /* FULL_NAME */ " +
                "AND EMAIL_ATTR.ATTR_ID = 2 /* EMAIL */ " +
                "AND PHONE_ATTR.ATTR_ID = 3 /* PHONE_NUMBER */ " ;

        List <Employee> employees=null;
        try {
            employees = jdbcTemplateObject.query(sql,
                    new EmployeeMapper());
            transactionManager.commit(status);
        }catch (DataAccessException e) {
            System.out.println("Error in select record, rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return employees;
    }

    @Override
    public List<OrderCursor> findOrdersByEmployeeId(int id) {
        logger.debug("Entering JdbcEmployeeDao.findOrdersByEmployeeId(employeeId=" + id + ")");
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        List<OrderCursor> orders;
        Map<String, Object> args = new HashMap<>(1);
        Map<String, Object> result = new HashMap<>(1);
        try {
            args.put("P_EMPLOYEE_ID", id);
            result.put("P_OUT_CURSOR", "");
            result = orderSelectByEmployeeSP.execute(args);
            orders = (List<OrderCursor>) result.get("P_OUT_CURSOR");
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            logger.error("Error selection orders, rolling back", ex);
            transactionManager.rollback(status);
            throw ex;
        }
        logger.debug("Leaving JdbcEmployeeDao.findOrdersByEmployeeId(employeeId=" + id + ")");
        return orders;
    }

}

