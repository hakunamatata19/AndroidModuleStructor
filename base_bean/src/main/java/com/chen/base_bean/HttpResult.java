package com.chen.base_bean;

public class HttpResult<T>{

    /**
     * 当前服务器返回的响应码
     */
    private int code;
    /**
     * 服务器返回的信息
     */
    private String msg;
    /**
     * 服务器返回的数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
