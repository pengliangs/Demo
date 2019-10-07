package com.github.pengliang.annotation.bean;

/**
 * @author pengliang  2019-03-21 18:28
 */
public class test2 extends test1  {

    @Override
    protected void initServletBean() {
        System.out.println("test2");
    }
}
