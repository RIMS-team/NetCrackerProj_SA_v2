package com.xvitcoder.springmvcangularjs.service.templates;

import com.xvitcoder.springmvcangularjs.model.AccessCard;
import com.xvitcoder.springmvcangularjs.model.Notebook;

import java.util.List;


/**
 * Created by Elina on 01.12.2016.
 */

 public interface InventoryServiceTempl {
     boolean addCard(AccessCard card);
     AccessCard getCardByCardId(int cardId);
     List<AccessCard> getAllCards();
     List<AccessCard> getCardsByStatus(String status);
     boolean delCard(AccessCard card);

     boolean addNotebook(Notebook notebook);
     Notebook getNotebookBySerialNumber(String serNumber);
     Notebook getNotebookByFinanceNumber(int finNumber);
     List<Notebook> getAllNotebooks();
     List<Notebook> getNotebooksByStatus(String status);
     List<Notebook> getNotebooksByLocation(String location);
     boolean delNotebook(Notebook notebook);
}