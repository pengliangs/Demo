package com.pl.web.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.security.Principal;

/**
 * author pengliang  2018-05-20 10:40
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/login/page")
    public String login() {
        return "/login";
    }

    @PostMapping("/user/login/form")
    public String loginForm() {
       return "";
    }



    @RequestMapping("/login/success")
    public String success() {
        return "/success";
    }


    @GetMapping("/my")
    @ResponseBody
    public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @GetMapping("/my2")
    @ResponseBody
    public Object getCurrentUser2(Authentication authentication){
        return authentication;
    }

    @GetMapping("/my3")
    @ResponseBody
    public Object getCurrentUser3(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }

}
