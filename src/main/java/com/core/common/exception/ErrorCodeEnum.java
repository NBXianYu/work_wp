package com.core.common.exception;

/***
 * @Author: 吴鹏
 * @Description: 自定义异常
 */
public enum ErrorCodeEnum {

    /**
     *
     */
    createFailError("1049", "创建失败，请刷新重试");

    private String code;
    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
