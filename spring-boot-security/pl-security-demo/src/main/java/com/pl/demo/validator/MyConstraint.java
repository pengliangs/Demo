package com.pl.demo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author pengliang  2018-05-06 16:39
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyValidator.class)
public @interface MyConstraint {
    /**
     * 以下3个属性必须定义的
     * @return
     */
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
