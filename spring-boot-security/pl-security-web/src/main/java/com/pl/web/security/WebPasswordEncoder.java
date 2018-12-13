package com.pl.web.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * author pengliang  2018-05-14 21:30
 * 自定义密码规则
 */
//@Component
public class WebPasswordEncoder implements PasswordEncoder {

    /**
     * 将用户密码加密处理
     *
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return null;
    }

    /**
     * 匹配用户传递过来的密码和加密后的密码是否匹配
     *
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return false;
    }
}
