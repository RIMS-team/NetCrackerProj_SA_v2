package com.xvitcoder.springmvcangularjs.service;

import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by trvler135 on 07.12.2016.
 */
public interface UserService {
    List<User> findAll();
    User findByEmail(String email);
    void addUser(User user);
    void deleteUser(int id);
}
