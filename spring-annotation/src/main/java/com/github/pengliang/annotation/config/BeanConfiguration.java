package com.github.pengliang.annotation.config;

import com.github.pengliang.annotation.bean.*;
import com.github.pengliang.annotation.bean.cycle.CycleBlue;
import com.github.pengliang.annotation.conditional.LinuxConditional;
import com.github.pengliang.annotation.conditional.WindowsConditional;
import com.github.pengliang.annotation.myImport.MyImportBeanDefinitionRegistrar;
import com.github.pengliang.annotation.myImport.MyImportSelector;
import org.springframework.context.annotation.*;

/**
 * @author pengliang  2019-03-14 15:57
 */
@ComponentScan("com.github.pengliang.annotation.bean.cycle")
@Configuration
@Import({Book.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class BeanConfiguration {

    @Bean
    public Person person() {
        return new Person("张三", 18,"小三");
    }

    @Scope(scopeName = "prototype")
    @Bean
    public PersonPrototype personPrototype() {
        return new PersonPrototype("张三", 18);
    }

    @Lazy
    @Bean
    public Animal animal() {
        return new Animal("张三", 18);
    }


    @Conditional(WindowsConditional.class)
    @Bean("bill")
    public Person person01() {
        return new Person("比尔盖茨", 60,"bill");
    }

    @Conditional(LinuxConditional.class)
    @Bean("linus")
    public Person person02() {
        return new Person("林纳斯", 60,"linus");
    }

    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public CycleBlue cycleBlue() {
        return new CycleBlue();
    }
}
