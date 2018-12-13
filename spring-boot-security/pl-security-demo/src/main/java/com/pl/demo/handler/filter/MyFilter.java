package com.pl.demo.handler.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * author pengliang  2018-05-06 21:34
 */

public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化MyFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("开始处理服务时间："+System.currentTimeMillis());
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("结束处理服务时间："+System.currentTimeMillis());
    }

    @Override
    public void destroy() {
        System.out.println("销毁MyFilter");
    }
}
