

package com.core.work.form;


/**
 * @Description: 登陆表单
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/19 0019 下午 14:06
 */
public class SysLoginForm {
    private String mobile;
    private String password;
    private String captcha;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
