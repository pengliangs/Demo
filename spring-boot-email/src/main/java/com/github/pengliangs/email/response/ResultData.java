package com.github.pengliangs.email.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author pengliang on 2018-12-13 11:00
 */
@Data
public final class ResultData<T> implements Serializable {

    private int code;

    private String message;

    private T t;

    public static <T> ResultData<T> newResultData(Integer code, String message) {
        return newResultData(code, message,null);
    }

    public static <T> ResultData<T> newResultData(Integer code, String message, T t) {
        return new ResultData(code, message, t);
    }

    private ResultData(Integer code, String message, T t) {
        this.code = code;
        this.message = message;
        this.t = t;
    }
}
