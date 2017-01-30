package com.xvitcoder.springmvcangularjs.service.impl;

import com.xvitcoder.springmvcangularjs.dao.impl.JdbcAccessCard;
import com.xvitcoder.springmvcangularjs.dao.impl.JdbcNotebook;
import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.service.AccessCardService;
import com.xvitcoder.springmvcangularjs.service.NotebookService;

import java.util.List;

/**
 * Created by Kristina on 08.12.2016.
 */
public class AccessCardServiceImpl implements AccessCardService {
    private JdbcAccessCard accessCardDao;

    public void setAccessCardDao(JdbcAccessCard accessCardDao) {
        this.accessCardDao = accessCardDao;
    }

    @Override
    public List<AccessCard> findAll() {
        return accessCardDao.findAll();
    }

    @Override
    public AccessCard findByInventoryNum(int inventoryNum) {
        return accessCardDao.findByInventoryNum(inventoryNum);
    }

    @Override
    public ErrorText addAccessCard(AccessCard card) {
        return accessCardDao.insert(card);
    }

    @Override
    public ErrorText deleteCard(int id) {
        return accessCardDao.deleteCard(id);
    }

    @Override
    public ErrorText updateCard(AccessCard card) {
        return accessCardDao.update(card);
    }

    @Override
    public List<AccessCard> findByStatus(int statusId) {
        return accessCardDao.findByStatus(statusId);
    }
}
