package com.github.pengliang.annotation;

import com.github.pengliang.annotation.config.BeanConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author pengliang  2019-03-19 16:47
 */
public class CycleTests {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void before(){
        applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    }

    @Test
    public void init_ioc(){
        applicationContext.close();
    }
}
