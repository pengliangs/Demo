package com.pl.demo.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.pl.demo.validator.MyConstraint;

/**
 * author pengliang  2018-05-06 11:34
 */
public class User {
    public interface SimpleUserInfo {
    }

    public interface DetailUserInfo extends SimpleUserInfo {
    }

    @MyConstraint(message = "username不可以为null")
    @JsonView(SimpleUserInfo.class)
    private String username;
    @JsonView(DetailUserInfo.class)
    private String password;

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
