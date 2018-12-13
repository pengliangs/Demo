package com.pl.demo.config;

import com.pl.demo.handler.filter.MyFilter;
import com.pl.demo.handler.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

/**
 * author pengliang  2018-05-06 21:33
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private MyInterceptor myInterceptor;

    @Bean
    public FilterRegistrationBean RegistrationMyFilter() {
        //创建注册器
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //将filter注册到主注册器中
        MyFilter myFilter = new MyFilter();
        registration.setFilter(myFilter);
        //设置过滤路径
        registration.setUrlPatterns(new ArrayList<String>() {{
            add("/*");
        }});
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor);
    }
}
