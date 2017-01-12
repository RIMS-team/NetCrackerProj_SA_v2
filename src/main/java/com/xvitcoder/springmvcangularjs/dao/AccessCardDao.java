package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.AccessCard;

import java.util.List;

/**
 * Created by Kristina on 05.12.2016.
 */
public interface AccessCardDao {

    public void deleteCard(int id);
    public void insert(AccessCard accessCard);
    public AccessCard findByInventoryNum(int cardId);
    public List<AccessCard> findAll();
}
