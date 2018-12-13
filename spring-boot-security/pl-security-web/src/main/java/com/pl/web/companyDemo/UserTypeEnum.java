package com.pl.web.companyDemo;

/**
 * author pengliang  2018-05-22 22:30
 */
public enum UserTypeEnum {

    ORDINARY_USER(0),
    ADMIN_USER(1),
    SUPER_ADMIN_USER(2);

    UserTypeEnum(Integer type){
        this.type = type;
    }

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
