package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.InventStatus;

import java.util.List;

/**
 * Created by dell on 08-Dec-16.
 */
public interface InventStatusDao {
    List<InventStatus> findAll();
    InventStatus findById(int id);
    ErrorText addStatus(InventStatus status);
    ErrorText updateStatus(InventStatus status);
}
