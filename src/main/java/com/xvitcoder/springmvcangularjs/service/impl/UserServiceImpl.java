package com.xvitcoder.springmvcangularjs.service.impl;


import com.xvitcoder.springmvcangularjs.dao.impl.JdbcUser;
import com.xvitcoder.springmvcangularjs.model.Admin;
import com.xvitcoder.springmvcangularjs.model.ErrorText;
import com.xvitcoder.springmvcangularjs.model.User;
import com.xvitcoder.springmvcangularjs.service.UserService;

import java.util.List;

/**
 * Created by trvler135 on 07.12.2016.
 */
public class UserServiceImpl implements UserService {

    private JdbcUser userDao;

    public void setUserDao(JdbcUser userDAO) {
        this.userDao = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Admin findByEmail(String email) {
        Admin user = userDao.findByEmail(email);
        if (user == null) return null;
        if (user.getRole().equals("2")) user.setRole("ROLE_USER");
        if (user.getRole().equals("3")) user.setRole("ROLE_ADMIN");
        return user;
    }

    @Override
    public ErrorText addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public ErrorText deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public ErrorText updateUser(User user) {
        return userDao.updateUser(user);
    }
}
