package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.AccessCard;

import java.util.List;

/**
 * Created by Kristina on 05.12.2016.
 */
public interface AccessCardDao {
    void deleteCard(int id);
    void insert(AccessCard accessCard);
    AccessCard findByInventoryNum(int cardId);
    List<AccessCard> findAll();
    void update(AccessCard card);
    List<AccessCard> findByStatus(int statusId);

}
