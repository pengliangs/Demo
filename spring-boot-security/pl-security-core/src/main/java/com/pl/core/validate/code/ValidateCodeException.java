package com.pl.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * author pengliang  2018-05-20 12:06
 */
public class ValidateCodeException extends AuthenticationException {



    public ValidateCodeException(String msg) {
        super(msg);
    }
}
