package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.Admin;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by trvler135 on 07.12.2016.
 */
public interface UserService {
    List<User> findAll();
    Admin findByEmail(String email);
    ErrorText addUser(User user);
    ErrorText deleteUser(int id);
    ErrorText updateUser(User user);
}
