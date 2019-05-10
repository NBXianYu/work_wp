

package com.core.work.form;


import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 登陆表单
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/19 0019 下午 14:06
 */
public class SysLoginForm {

    @ApiModelProperty(name = "userName", value = "登录用户名", dataType = "String")
    private String userName;

    @ApiModelProperty(name = "password", value = "密码", dataType = "String")
    private String password;

//    @ApiModelProperty(name = "captcha", value = "验证码（暂时不要，登录懒得输入）", dataType = "String")
//    private String captcha;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
