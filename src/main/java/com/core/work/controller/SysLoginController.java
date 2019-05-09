package com.core.work.controller;

import com.core.work.entity.SysUserEntity;
import com.core.work.entity.form.SysLoginForm;
import com.core.work.entity.form.UserForm;
import com.core.work.service.SysUserService;
import com.core.work.service.SysUserTokenService;
import com.core.work.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/logout")
    @ApiOperation(value = "登出", notes = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
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

        if (sysUserService.queryByName(userForm.getUserName()) != null || sysUserService.queryByMobile(userForm.getPhone()) != null) {
            return Result.error("当前用户名或手机号已注册，请重新输入");
        }

        sysUserService.addSysUser(UserForm.getUserByUserForm(null, userForm));
        return Result.ok().putResult("注册成功");
    }

    @ApiOperation(value = "用户修改密码", notes = "用户修改密码")
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody UserForm userForm){
        System.out.println(userForm.toString());

        if (userForm.getUserName().isEmpty() || userForm.getPhone().isEmpty()) {
            return Result.error("用户名或电话号码错误");
        }

        if (userForm.getPassword() == null || userForm.getPassword().length() < 6) {
            return Result.error("密码长度需要大于6位");
        }

        SysUserEntity user = sysUserService.queryByName(userForm.getUserName());

        if (user == null || !user.getPhone().equals(userForm.getPhone())) {
            return Result.error("用户名或电话号码错误");
        }

        sysUserService.save(UserForm.changePasswordByUserForm(user, userForm));

        return Result.ok().putResult("注册成功");
    }

}
