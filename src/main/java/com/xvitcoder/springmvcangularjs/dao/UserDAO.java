package com.xvitcoder.springmvcangularjs.dao;


import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by trvler135 on 06.12.2016.
 */
public interface UserDAO {
    List<User> findAll();
    User findByEmail(String email);
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
}
