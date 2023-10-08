package com.chen.base_http.base;

import com.chen.base_bean.HttpResult;
import com.chen.base_utils.KLog;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HttpResultCallAdapterFactory extends CallAdapter.Factory {
    private static final String TAG = "HttpResultCallAdapterFa";

    private static HttpResultCallAdapterFactory mInstance = null;

    public synchronized static CallAdapter.Factory create() {
        if (mInstance == null) {
            mInstance = new HttpResultCallAdapterFactory();
        }
        return mInstance;
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != HttpResult.class) {
            return null;
        }

        return new HttpResultAdapter(returnType);
    }


    public class HttpResultAdapter implements CallAdapter {
        private Type responseType;

        public HttpResultAdapter(Type type) {
            responseType = type;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @Override
        public Object adapt(Call call) {
            HttpResult body = null;
            try {
                KLog.d(TAG,"currentThread： " +Thread.currentThread().getName());
                Response execute = call.execute();
                if (execute.isSuccessful()) {
                    body = (HttpResult) execute.body();
                } else {
                    body = new HttpResult();
                    body.setCode(-execute.code());
                    body.setMsg(execute.message());
                }
            } catch (Exception e) {
                HttpExecption ex = HttpExecption.parseException(e);
                body = new HttpResult<>();
                body.setCode(ex.getCode());
                body.setMsg(ex.getMsg());
            } finally {
                return body;
            }


        }
    }

}
