package com.chen.base_utils;

import static android.util.Log.getStackTraceString;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author chenguo QQ: 
 * @name SingDoc
 * @package name：com.coocaa.karaoke.utils
 * @time 2020/9/24 21:14
 * @chang time
 * @describe describe
 */
public class StringFormatUtil {
    private StringFormatUtil() {

    }
    private  static SimpleDateFormat mFormatter=new SimpleDateFormat("mm:ss");

    public static String getTimeShort(long time) {
        try {

            String dateString = mFormatter.format(time);
            return dateString;
        } catch (Exception e) {
            return "";
        }

    }

    public static String date() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(System.currentTimeMillis());
            return dateString;
        } catch (Exception e) {
            return "";
        }

    }
    public static String toString(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof Throwable) {
            return getStackTraceString((Throwable) object);
        }

        if (!object.getClass().isArray()) {
            return object.toString();
        }

        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }
        if (object instanceof Object[]) {
            return Arrays.deepToString((Object[]) object);
        }
        return "Couldn't find a correct type for the object";
    }
}
