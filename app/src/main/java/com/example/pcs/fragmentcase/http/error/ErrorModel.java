package com.example.pcs.fragmentcase.http.error;

/**
 * 请求失败的Model
 *
 * @author Starry Jerry
 * @since 18-3-14.
 */

public class ErrorModel {

    private int code;

    private String message;

    public ErrorModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public ErrorModel setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorModel setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "ErrorModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
