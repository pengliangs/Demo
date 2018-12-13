package com.pl.demo.service.imp;

import com.pl.demo.bean.User;
import com.pl.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author pengliang  2018-05-06 16:58
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserList(String username) {
        return new ArrayList<User>() {{
            add(new User("张三", "123"));
            add(new User("李四", "456"));
            add(new User("王五", "789"));
            add(new User(username,"xxx"));
        }};
    }

    @Override
    public User getUserInfo(Integer id) {
        return new ArrayList<User>() {{
            add(new User("张三", "123"));
            add(new User("李四", "456"));
            add(new User("王五", "789"));
        }}.get(id);
    }

    @Override
    public User insertUser(User user) {
        return user;
    }
}
