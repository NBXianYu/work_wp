

package com.core.modules.sys.entity.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 登陆表单
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/19 0019 下午 14:06
 */
@Data
public class SysLoginForm {

    @ApiModelProperty(name = "userName", value = "登录用户名", dataType = "String")
    private String userName;

    @ApiModelProperty(name = "password", value = "密码", dataType = "String")
    private String password;

//    @ApiModelProperty(name = "captcha", value = "验证码（暂时不要，登录懒得输入）", dataType = "String")
//    private String captcha;

}
