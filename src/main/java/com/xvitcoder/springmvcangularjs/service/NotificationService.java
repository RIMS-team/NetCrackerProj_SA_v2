package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.Notebook;
import com.xvitcoder.springmvcangularjs.model.Notification;

import java.util.List;

/**
 * Created by Admin on 18.01.2017.
 */
public interface NotificationService {
    List<Notification> findAll();
}
