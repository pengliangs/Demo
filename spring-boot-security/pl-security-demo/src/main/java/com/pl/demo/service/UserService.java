package com.pl.demo.service;

import com.pl.demo.bean.User;

import java.util.List;

/**
 * author pengliang  2018-05-06 16:57
 */
public interface UserService {

    List<User> getUserList(String username);

    User getUserInfo(Integer id);

    User insertUser(User user);
}
