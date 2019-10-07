package com.github.pengliang.annotation.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author pengliang  2019-03-14 17:50
 */
@Data
@ToString
@NoArgsConstructor
public class Animal {

    private String name;

    private int age;

    public Animal(String name, int age) {
        System.out.println("Create Animal...");
        this.name = name;
        this.age = age;
    }
}
