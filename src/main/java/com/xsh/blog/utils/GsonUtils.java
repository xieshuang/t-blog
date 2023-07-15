package com.xsh.blog.utils;

import com.google.gson.Gson;

/**
 * json转换工具
 */
public class GsonUtils {

    private static final Gson gson = new Gson();

    public static String toJsonString(Object object){
      return object==null?null:gson.toJson(object);
    }
}
