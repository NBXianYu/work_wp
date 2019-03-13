

package com.core.work.form;


/**
 * @author: 成都阳帆网络科技有限公司
 * @date: 2018/8/21 0021 10:26
 * @description: 登陆表单
 */
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
    private String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
