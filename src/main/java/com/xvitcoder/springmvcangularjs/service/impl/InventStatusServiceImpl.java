package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcInventStatus;
import com.xvitcoder.springmvcangularjs.model.InventStatus;
import com.xvitcoder.springmvcangularjs.service.InventStatusService;

import java.util.List;

/**
 * Created by dell on 08-Dec-16.
 */
public class InventStatusServiceImpl implements InventStatusService {
    private JdbcInventStatus inventStatusDao;

    public void setInventStatusDao(JdbcInventStatus inventStatusDAO) {
        this.inventStatusDao = inventStatusDAO;
    }

    @Override
    public List<InventStatus> findAll() {
        return inventStatusDao.findAll();
    }

    @Override
    public InventStatus findById(int id) {
        return inventStatusDao.findById(id);
    }
}
