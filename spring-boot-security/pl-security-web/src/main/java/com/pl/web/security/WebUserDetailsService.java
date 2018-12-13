package com.pl.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * author pengliang  2018-05-14 20:56
 */
@Component
public class WebUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(WebUserDetailsService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户名：" + username);
        /**
         * 这里使用的是security提供的User类
         * 当然在实际的应用中，我们都有自己User类；
         * 如果不用Security提供的User那么我们可以通过实现UserDetails定义自己的User类
         */
        String password = passwordEncoder.encode("123456");
        logger.info("密码：" + password);
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
