package com.core.modules.sys.entity.form;

import com.core.common.validator.group.AddGroup;
import com.core.common.validator.group.UpdateGroup;
import com.core.modules.sys.entity.SysUserEntity;
import com.core.common.exception.BaseException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.validation.constraints.NotBlank;

/**
 * @Author 吴鹏
 * @Date Created in 上午 11:31 2019/3/19 0019
 * @Email 694798354@qq.com
 * @Description:
 */
@Data
public class UserForm {

    @ApiModelProperty(name = "id", value = "id(修改用户才传)", dataType = "String")
    private String id;

    @ApiModelProperty(name = "phone", value = "手机号", dataType = "String")
    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String phone;

    @ApiModelProperty(name = "userName", value = "用户名", dataType = "String")
    private String userName;

    @ApiModelProperty(name = "password", value = "密码", dataType = "String")
    private String password;

    public static SysUserEntity getUserByUserForm(SysUserEntity sysUserEntity, UserForm userForm) {
        if (StringUtils.isBlank(userForm.getPassword()) || userForm.getPassword().length() < 6){
            throw new BaseException("密码不能为空，且长度大于6");
        }
        if (StringUtils.isBlank(userForm.getUserName())) {
            throw new BaseException("用户名不能为空");
        }
//        if (StringUtils.isBlank(userForm.getPhone())) {
//            throw new BaseException("电话号码不能为空");
//        }
        if (sysUserEntity == null) {
            sysUserEntity = new SysUserEntity();
        }
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        sysUserEntity.setPassWord(new Sha256Hash(userForm.getPassword(), salt).toHex());
        sysUserEntity.setSalt(salt);
        sysUserEntity.setPhone(userForm.getPhone());
        sysUserEntity.setUserName(userForm.getUserName());
        return sysUserEntity;
    }
}
