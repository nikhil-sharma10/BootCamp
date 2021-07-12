package com.bootcampProject.BootcampProject.common;

import org.springframework.stereotype.Component;

import java.util.List;


public class ResponseBody<T, String> {
    T data;
    String message;


    public ResponseBody() {
    }

    public ResponseBody(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
