package com.github.pengliang.annotation.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @author pengliang  2019-03-13 20:46
 */
@Data
@ToString
public class PersonPrototype {

    private String name;

    private int age;

    public PersonPrototype(){
        System.out.println("new PersonPrototype()");
    }

    public PersonPrototype(String name, int age) {
        System.out.println("new PersonPrototype()");
        this.name = name;
        this.age = age;
    }
}
