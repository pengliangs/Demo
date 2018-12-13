package com.pl.demo.validator;

import com.pl.demo.bean.User;
import com.pl.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * author pengliang  2018-05-06 16:38
 */
public class MyValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private UserService service;

    @Override
    public void initialize(MyConstraint myConstraint) {
        System.out.println("initialize");
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        User user = service.getUserInfo(1);
        System.out.println(user.getUsername());
        System.out.println(s);
        if (s == null){
            return false;
        }
        return true;
    }
}
