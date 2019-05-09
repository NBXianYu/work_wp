package com.core.work.controller;

import com.core.work.entity.SysUserEntity;
import com.core.work.entity.form.UserForm;
import com.core.work.service.SysUserService;
import com.core.work.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(description = "用户登录接口")
@RestController
@RequestMapping("/user")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/findAll")
    public Result addLogin() {
        return Result.ok().putResult(sysUserService.findAll());
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PutMapping("/update")
    public Result update(@RequestBody UserForm userForm) {
        //如果要修改用户名，但是用户名已经存在
        if (!StringUtils.isNotBlank(userForm.getUserName())
                && sysUserService.queryByName(userForm.getUserName()) != null
                && !sysUserService.queryByName(userForm.getUserName()).getId().equals(userForm.getId())) {
            return Result.error("用户名已存在");
        }

        //如果要修改密码，但是不符合要求
        if (!StringUtils.isBlank(userForm.getPassword()) && userForm.getPassword().length() < 6) {
            //如果需要修改但是密码不符合要求
            return Result.error("密码长度必须大于6位");
        }

        //如果要修改手机号，但是手机号已经存在
        if (!StringUtils.isNotBlank(userForm.getUserName())
                && sysUserService.queryByMobile(userForm.getPhone()) != null
                && !sysUserService.queryByMobile(userForm.getPhone()).getId().equals(userForm.getId())) {
            return Result.error("该手机已注册另外的账号");
        }

        SysUserEntity sysUserEntity = sysUserService.findById(userForm.getId());

        if (sysUserEntity == null) {
            return Result.error("该用户不存在");
        }
        sysUserService.save(UserForm.updateUserByUserForm(sysUserEntity, userForm));
        return Result.ok().putResult("修改成功");
    }

}
