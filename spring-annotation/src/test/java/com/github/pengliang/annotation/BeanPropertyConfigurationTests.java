package com.github.pengliang.annotation;

import com.github.pengliang.annotation.bean.Person;
import com.github.pengliang.annotation.config.BeanPropertyConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author pengliang  2019-03-19 18:12
 */
public class BeanPropertyConfigurationTests {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void before(){
        applicationContext = new AnnotationConfigApplicationContext(BeanPropertyConfiguration.class);
    }

    @Test
    public void person_value(){
       Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
        Assert.assertEquals("配置文件",person.getNickname());
    }
}
