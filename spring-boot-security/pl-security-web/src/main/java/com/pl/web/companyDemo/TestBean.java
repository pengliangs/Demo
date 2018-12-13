package com.pl.web.companyDemo;

import java.util.List;

/**
 * author pengliang  2018-05-22 22:42
 */
public class TestBean {

    private String name;

    private Integer age;

    private List<TestOrg> orgList;

    public List<TestOrg> getOrgList() {
        return orgList;
    }

    public void setOrgList(List<TestOrg> orgList) {
        this.orgList = orgList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
