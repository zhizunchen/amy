package com.example.demo.exception;

import lombok.Getter;

/**
 * @Author chenhe
 * @Date 2019/6/27 17:41
 * @Description 自定义运行时异常
 */
public class BusException extends RuntimeException {

    @Getter
    private int code;

    public BusException(String msg){
        super(msg);
    }

    public BusException(int code, String msg){
        super(msg);
        this.code = code;
    }

    public BusException(int code, String msg, Exception e){
        super(msg, e);
        this.code = code;
    }
}
