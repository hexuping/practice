package com.example.core.entity.vo;

import lombok.Data;

@Data
public class Result<T> {

    private String code;

    private String message;

    private T data;

    public Result() {
        this.code = "000000";
        this.message = "SUCCESS";
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public static Result error(String code, String message) {
        return new Result<>(code, message);
    }

    public static <T> Result<T> error(String code, String message, T data) {
        Result<T> result = new Result<>(code, message);
        result.setData(data);
        return result;
    }

}
