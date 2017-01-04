package com.xvitcoder.springmvcangularjs.service.impl;


import com.xvitcoder.springmvcangularjs.dao.impl.JdbcNotebook;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.service.NotebookService;

import java.util.List;

/**
 * Created by Elina on 08.12.2016.
 */
public class NotebookServiceImpl implements NotebookService {
    private JdbcNotebook notebookDao;

    public void setNotebookDao(JdbcNotebook notebookDao) {
        this.notebookDao = notebookDao;
    }

    @Override
    public List<Notebook> findAll() {
        return notebookDao.findAll();
    }
}
