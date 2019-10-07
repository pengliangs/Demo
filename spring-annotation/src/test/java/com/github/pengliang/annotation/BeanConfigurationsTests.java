package com.github.pengliang.annotation;

import com.github.pengliang.annotation.bean.*;
import com.github.pengliang.annotation.config.BeanConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author pengliang  2019-03-13 20:52
 */
public class BeanConfigurationsTests {

    private ApplicationContext applicationContext;

    @Before
    public void before() {
        applicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        System.out.println("初始化容器...");
    }


    private void printSpringIocBeanName() {
        Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
    }

    @Test
    public void person_bean_get() {
        Person person1 = (Person) applicationContext.getBean("person");
        assertNotNull(person1);
        assertEquals("张三", person1.getName());
        assertEquals(18, person1.getAge());
    }

    @Test
    public void person_bean_is_singleton_result_true() {
        Person person1 = (Person) applicationContext.getBean("person");
        Person person2 = (Person) applicationContext.getBean("person");
        assertEquals(Boolean.TRUE, person1 == person2);
    }

    @Test
    public void person_bean_is_prototype_result_true() {
        PersonPrototype person1 = (PersonPrototype) applicationContext.getBean("personPrototype");
        PersonPrototype person2 = applicationContext.getBean(PersonPrototype.class);
        assertEquals(Boolean.FALSE, person1 == person2);
    }

    @Test
    public void animal_lazy_test() {
        Animal animal = applicationContext.getBean(Animal.class);
        assertNotNull(animal);
    }


    @Test
    public void conditional_annotation_test() {
        Map<String, Person> personMap = applicationContext.getBeansOfType(Person.class);
        personMap.forEach((k, v) -> System.out.println(k + "=" + v));
    }

    @Test
    public void book_bean_import() {
        printSpringIocBeanName();
    }


    @Test
    public void factory_bean_import_bean() {
        Object obj = applicationContext.getBean("colorFactoryBean");
        assertEquals(Color.class, obj.getClass());
        obj = applicationContext.getBean("&colorFactoryBean");
        assertEquals(ColorFactoryBean.class, obj.getClass());
    }
}
