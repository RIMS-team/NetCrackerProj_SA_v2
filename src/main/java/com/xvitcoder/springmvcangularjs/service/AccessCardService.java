package com.xvitcoder.springmvcangularjs.service;


import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by Kristina on 08.12.2016.
 */
public interface AccessCardService {
    List<AccessCard> findAll();
    AccessCard findByInventoryNum(int inventoryNum);
    ErrorText addAccessCard(AccessCard card);
    ErrorText deleteCard(int id);
    ErrorText updateCard(AccessCard card);
    List<AccessCard> findByStatus(int statusId);
}
