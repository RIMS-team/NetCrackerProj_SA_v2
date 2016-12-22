package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.AccessCard;

import java.util.List;

/**
 * Created by Kristina on 05.12.2016.
 */
public interface AccessCardDao {

    public void insert(AccessCard accessCard);
    public AccessCard findByCardId(int cardId);
    public List<AccessCard> findAllCards();
}
