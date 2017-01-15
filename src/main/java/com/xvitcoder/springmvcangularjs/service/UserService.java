package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.Admin;
import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by trvler135 on 07.12.2016.
 */
public interface UserService {
    List<User> findAll();
    Admin findByEmail(String email);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
