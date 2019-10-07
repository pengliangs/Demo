package com.github.pengliang.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.Objects;

/**
 * @author pengliang  2019-03-13 20:46
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    /**
     * @Value 可以是
     * 1.基本数据类型
     * 2.SpEl表达式 #{}
     * 3.读取配置文件 ${}
     */
    @Value("默认张三")
    private String name;
    @Value("#{10+8}")
    private int age;
    @Value("${bean.person.nickname}")
    private String nickname;

}
