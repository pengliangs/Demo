package com.github.pengliang.annotation.config;

import com.github.pengliang.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author pengliang  2019-03-19 18:10
 */
@PropertySource("classpath:/person-value.properties")
@Configuration
public class BeanPropertyConfiguration {

    @Bean
    public Person person(){
        return new Person();
    }
}
