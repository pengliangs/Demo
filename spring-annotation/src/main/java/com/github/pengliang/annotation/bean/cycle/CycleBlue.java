package com.github.pengliang.annotation.bean.cycle;

/**
 * @author pengliang  2019-03-19 16:30
 */
public class CycleBlue {

    public void init(){
        System.out.println("CycleBlue->init-method");
    }

    public void destroy(){
        System.out.println("CycleBlue->destory-method");
    }
}
