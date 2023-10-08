package com.chen.base_http.base;

import com.chen.base_utils.KLog;


import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by luwei on 16-10-19.
 */

public class HttpExecption extends RuntimeException {
    public static final int HTTP_CODE_TIME_OUT = -600;

    public static final int HTTP_CODE_UNKNOWN_HOST = -900;

    public static final int HTTP_CODE_OTHER_CODE = -700;

    public static final String MSG_HTTP_ERROR_TIME_OUT = "请求超时，请检查网络是否正常。";

    public static final String MSG_HTTP_ERROR_UNKNOWN_HOST = "网络异常！";

    public static final String MSG_HTTP_ERROR_OTHER_CODE = "其它异常。";

    public static HttpExecption parseException(Throwable e) {
        KLog.e(e);
        if (e instanceof SocketTimeoutException) {
            return new HttpExecption(MSG_HTTP_ERROR_TIME_OUT, HTTP_CODE_TIME_OUT);
        } else if (e instanceof UnknownHostException) {
            return new HttpExecption(MSG_HTTP_ERROR_UNKNOWN_HOST, HTTP_CODE_UNKNOWN_HOST);
        } else {
            return new HttpExecption(MSG_HTTP_ERROR_OTHER_CODE, HTTP_CODE_OTHER_CODE);
        }
    }


    private int code = 0;
    private String msg = "";

    public HttpExecption(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public HttpExecption(Throwable e) {
        super(e);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return super.toString() + ", msg=" + msg + ", code=" + code;
    }
}
