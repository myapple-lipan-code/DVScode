package com.ljs.callray.controller.model;

import java.io.Serializable;

/**
 * @author LiJinSong
 * @date 2021-01-28
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;

    private Integer code;

    private String msg;

    public Result() {
    }

    public Result(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "data=" + data +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
