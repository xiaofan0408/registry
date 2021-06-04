package com.xiaofan0408.v1.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T>{

    private int code;

    private String message;

    private T data;
}
