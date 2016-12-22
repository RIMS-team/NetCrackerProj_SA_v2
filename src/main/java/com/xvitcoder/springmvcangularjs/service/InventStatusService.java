package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.InventStatus;

import java.util.List;

/**
 * Created by dell on 08-Dec-16.
 */
public interface InventStatusService {
    List<InventStatus> findAll();
    InventStatus findById(int id);
}
