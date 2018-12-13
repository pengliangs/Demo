package com.pl.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.pl.core.validate.code.ValidateImageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * author pengliang  2018-05-13 10:32
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SuccessHandler successHandler;
    @Autowired
    private FailureHandler failureHandler;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 配置如何通过拦截器保护请求
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new ValidateImageFilter(failureHandler), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //登陆页面
                .loginPage("/user/login/page")
                .loginProcessingUrl("/user/login/form")
                .successHandler(successHandler)
                .defaultSuccessUrl("/user/login/success")
                .failureHandler(failureHandler)
                .and()
                .authorizeRequests()
                //匹配器当，匹配当路径为/login.html是不进行身份认证
                .antMatchers("/login.html"
                        , "/user/login/page"
                        , "/code/image").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆成功后的处理
     */
    @Component
    public class SuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
            System.out.println("登陆成功");
        }
    }

    /**
     * 登陆失败后的处理
     */
    @Component
    public class FailureHandler implements AuthenticationFailureHandler {

        @Override
        public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
            System.out.println("登陆失败");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(e.getMessage()));
        }
    }
}
