package com.core.work.controller;

import com.core.work.entity.SysUserEntity;
import com.core.work.form.SysLoginForm;
import com.core.work.form.UserForm;
import com.core.work.service.SysUserService;
import com.core.work.service.SysUserTokenService;
import com.core.work.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: 登录相关
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/3/13 0013 上午 11:07
 */
@RestController
@Api(value = "后台登陆")
@RequestMapping("/sys")
public class SysLoginController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    /**
     * 登录
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/login")
    @ApiOperation(value = "登陆", notes = "登陆")
    public Map<String, Object> login(@RequestBody SysLoginForm form) throws IOException {

        //用户信息
        SysUserEntity user = sysUserService.queryByName(form.getUserName());
        //账号不存在、密码错误
        if (user == null || !user.getPassWord().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
            return Result.error("账号或密码不正确");
        }

        //账号锁定
        if (user.getStatus() == 0) {
            return Result.error("账号已被锁定,请联系管理员");
        }

        //生成token，并保存到数据库
        Result result = sysUserTokenService.createToken(user.getId());
        return result;
    }


    /**
     * 退出
     */
    @PostMapping("/logout")
    @ApiOperation(value = "登出", notes = "登出")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.ok();
    }

    /**
     * @Description: 注册
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [userForm]
     * @return com.core.work.utils.Result
     * @date 2019/3/19 0019 下午 14:21
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @PostMapping("/regist")
    public Result regist(@RequestBody UserForm userForm){
        sysUserService.addSysUser(UserForm.getUserByUserForm(null, userForm));
        return Result.ok().putResult("注册成功");
    }

}
