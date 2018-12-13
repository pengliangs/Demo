package com.pl.web.companyDemo;

import java.lang.annotation.*;

/**
 * author pengliang  2018-05-22 22:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface OaUserTypeHandler {


    Class paramClass();

    OaUserTypeParam[] params();
}
