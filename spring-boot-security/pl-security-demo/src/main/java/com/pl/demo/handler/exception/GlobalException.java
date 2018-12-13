package com.pl.demo.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * author pengliang  2018-05-06 18:41
 */
@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handRuntimeException(RuntimeException e) {
        return new HashMap<String, Object>() {{

            put("error", e.getLocalizedMessage());
        }};
    }
}
