package com.xvitcoder.springmvcangularjs.dao;

import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.ErrorText;

import java.util.List;

/**
 * Created by Kristina on 05.12.2016.
 */
public interface AccessCardDao {
    ErrorText deleteCard(int id);
    ErrorText insert(AccessCard accessCard);
    AccessCard findByInventoryNum(int cardId);
    List<AccessCard> findAll();
    ErrorText update(AccessCard card);
    List<AccessCard> findByStatus(int statusId);

}
