package com.xvitcoder.springmvcangularjs.service.impl;


import com.xvitcoder.springmvcangularjs.dao.impl.JdbcUser;
import com.xvitcoder.springmvcangularjs.model.Admin;
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
        if (user.getRole().equals("2")) user.setRole("ROLE_USER");
        if (user.getRole().equals("3")) user.setRole("ROLE_ADMIN");
        return user;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
