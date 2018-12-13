package com.pl.web.companyDemo;

import java.util.List;

/**
 * author pengliang  2018-05-22 22:57
 */
public class LoginUser {

    private String name;

    private TestOrg testOrg;

    private List<TestOrg> testOrgList;

    private Boolean admin;

    private Boolean superAdmin;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public List<TestOrg> getTestOrgList() {
        return testOrgList;
    }

    public void setTestOrgList(List<TestOrg> testOrgList) {
        this.testOrgList = testOrgList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestOrg getTestOrg() {
        return testOrg;
    }

    public void setTestOrg(TestOrg testOrg) {
        this.testOrg = testOrg;
    }
}
