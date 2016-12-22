package com.xvitcoder.springmvcangularjs.service.templates;


import com.xvitcoder.springmvcangularjs.model.Employee;
import com.xvitcoder.springmvcangularjs.model.User;

import java.util.List;

/**
 * Created by Elina on 01.12.2016.
 */
public interface UserServiceTempl {
    boolean addUser(Employee employee);

    User getUserByEmail(String email); // check if user exists

    boolean logIn(User user);

    boolean logOut(User user);

    boolean changePassword(User user, String password); // password - new password

    boolean delUser(User user);

    List<User> getAllUsers();
}
