package com.xvitcoder.springmvcangularjs.service.impl;

import app.dao.impl.JdbcNotebook;
import app.model.Notebook;
import app.service.NotebookService;

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
