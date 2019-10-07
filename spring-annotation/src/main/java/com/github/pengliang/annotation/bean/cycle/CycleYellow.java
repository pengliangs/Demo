package com.github.pengliang.annotation.bean.cycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author pengliang  2019-03-19 16:52
 */
@Component
public class CycleYellow {

    @PostConstruct
    public void init(){
        System.out.println("CycleYellow->@PostConstruct");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("CycleYellow->@PreDestroy");
    }

}
