package com.pl.web.companyDemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * author pengliang  2018-05-22 22:33
 */
@Aspect
@Component
public class UserOperationAspect {


    @Before("@annotation(oaUserTypeHandler)")
    public void MultiplePersonality(JoinPoint joinPoint, OaUserTypeHandler oaUserTypeHandler) throws NoSuchFieldException, IllegalAccessException {
        LoginUser loginUser = new LoginUser();
        loginUser.setName("张三");
        loginUser.setAdmin(false);
        loginUser.setSuperAdmin(false);
        TestOrg testOrg = new TestOrg();
        testOrg.setName("testorg");
        testOrg.setAge(18);
        loginUser.setTestOrg(testOrg);
        List<TestOrg> list = new ArrayList<TestOrg>();
        list.add(testOrg);
        TestOrg testOrg2 = new TestOrg();
        testOrg2.setName("testorg2");
        testOrg2.setAge(26);
        list.add(testOrg2);
        loginUser.setTestOrgList(list);


        Object[] args = joinPoint.getArgs();
        if (args == null && args.length == 0) {
            return;
        }

        Class paramClass = oaUserTypeHandler.paramClass();
        OaUserTypeParam[] params = oaUserTypeHandler.params();
        Class loginUserClass = loginUser.getClass();
        for (Object object : args) {
            if (object == null) {
                continue;
            }
            System.out.println(object.getClass());
            if (paramClass.equals(object.getClass())) {
                for (OaUserTypeParam param : params) {
                    OaUserTypeParam handler = null;
                    int type = 0;
                    if (loginUser.getSuperAdmin()) {
                        type = 2;
                    }

                    if (loginUser.getAdmin()) {
                        type = 1;
                    }

                    for (UserTypeEnum typeEnum : param.type()) {
                        if (typeEnum.getType() == type) {
                            handler = param;
                        }
                    }

                    if (handler == null) {
                        continue;
                    }

                    //当前参数对象
                    Class thisObj = object.getClass();
                    Field field = thisObj.getDeclaredField(param.property());
                    field.setAccessible(true);

                    //获取配置注入属性值
                    String[] fieldNames = param.value().split("[.]");
                    Object obj = findFieldName(loginUser, loginUserClass, fieldNames, 0);
                    field.set(object, obj);
                }
                break;
            }
        }
    }

    private Object findFieldName(Object object, Class cls, String[] fieldNames, int startIndex) throws NoSuchFieldException, IllegalAccessException {
        int size = fieldNames.length - 1;
        Field loginField = cls.getDeclaredField(fieldNames[startIndex]);
        loginField.setAccessible(true);
        Object obj = loginField.get(object);
        if (size == startIndex) {
            return obj;
        }
        return findFieldName(obj, obj.getClass(), fieldNames, ++startIndex);
    }
}
