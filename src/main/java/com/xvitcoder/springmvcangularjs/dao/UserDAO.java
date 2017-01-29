package com.xvitcoder.springmvcangularjs.dao;


import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by trvler135 on 06.12.2016.
 */
public interface UserDAO {
    List<User> findAll();
    User findByEmail(String email);
    ErrorText addUser(User user);
    ErrorText deleteUser(int id);
    ErrorText updateUser(User user);
}
