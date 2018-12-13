package com.pl.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.pl.demo.bean.User;
import com.pl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author pengliang  2018-05-06 08:34
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    /**
     * 返回不包含密码
     */
    @RequestMapping("/query")
    @JsonView(User.SimpleUserInfo.class)
    public List<User> query(@RequestParam String username) {
        return userService.getUserList(username);
    }

    /**
     * 返回包含密码
     */
    @RequestMapping("/{id:\\d+}")
    @JsonView(User.DetailUserInfo.class)
    public User userInfo(@PathVariable(name = "id") Integer id) {
        System.out.println("执行userInfo");
        return userService.getUserInfo(id);
    }

    @RequestMapping("/insert")
    @JsonView(User.DetailUserInfo.class)
    public User insertUser(@Validated @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
        }
        return userService.insertUser(user);
    }
}
