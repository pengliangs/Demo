package com.github.pengliang.annotation;

import com.github.pengliang.annotation.config.BeanConfiguration;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author pengliang  2019-03-14 15:58
 */
public class AnnotationConfigurationsTests {

    private ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
    }


}
