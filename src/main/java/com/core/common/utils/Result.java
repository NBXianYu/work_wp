package com.core.common.utils;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 统一返回类
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/2/20 0020 下午 17:46
 */
public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    private static final String CODE = "code";

    private static final String MSG = "msg";

    private static final String RESULT = "result";

    private static final String SUCCESS = "success";

    public Result() {
        put(CODE, HttpStatus.SC_OK);
        put(MSG, SUCCESS);
    }

    public Result putResult(Object value) {
        super.put(RESULT, value);
        return this;
    }

    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put(CODE, code);
        result.put(MSG, msg);
        result.put("state", false);
        return result;
    }


    public static Result ok(String msg) {
        Result result = new Result();
        result.put(MSG, msg);
        result.put("state", true);
        return result;
    }

    public static Result ok(Map<String, Object> map) {
        Result result = new Result();
        result.putAll(map);
        return result;
    }

    public static Result ok() {
        return new Result();
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Result putTotal(Object value) {
        super.put("total", value);
        return this;
    }

}
