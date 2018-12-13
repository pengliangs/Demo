package com.pl.web.companyDemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author pengliang  2018-05-22 22:36
 */
@RestController
public class TestController {


    @GetMapping("/test1")
    @OaUserTypeHandler(paramClass = TestBean.class, params = {
            @OaUserTypeParam(property = "name", value = "testOrg.name",type = {
                    UserTypeEnum.ORDINARY_USER,UserTypeEnum.ADMIN_USER
            }),
            @OaUserTypeParam(property = "age", value = "testOrg.age"),
            @OaUserTypeParam(property = "orgList", value = "testOrgList")
    })
    public String test1(TestBean testBean) {
        return "success";
    }

    @GetMapping("/test2")
    @OaUserTypeHandler(paramClass = String.class, params = {
            @OaUserTypeParam(property = "testBean", value = "name"),
            @OaUserTypeParam(property = "name2", value = "testOrg.name")
    })
    public String test2(String testBean,String name2) {
        return "success";
    }
}
