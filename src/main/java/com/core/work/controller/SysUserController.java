package com.core.work.controller;

import com.core.work.entity.SysUserEntity;
import com.core.work.exception.BaseException;
import com.core.work.form.UserForm;
import com.core.work.utils.Result;
import com.core.work.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @date 2019/1/28 0028 下午 15:21
 */
@Api(description = "用户登录接口")
@RestController
@RequestMapping("/user")
public class SysUserController extends AbstractController{
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/findAll")
    public Result addLogin() {
        return Result.ok().putResult(sysUserService.findAll());
    }

    /**
     * @Description: 修改用户信息
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [userForm]
     * @return com.core.work.utils.Result
     * @date 2019/3/19 0019 下午 14:21
     */
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @PutMapping("/update")
    public Result update(@RequestBody UserForm userForm){
        if (StringUtils.isBlank(userForm.getId())) {
            throw new BaseException("id不能为空");
        }
        SysUserEntity sysUserEntity = sysUserService.findById(userForm.getId());
        if (sysUserEntity == null) {
            throw new BaseException("用户未找到");
        }
        sysUserService.save(UserForm.getUserByUserForm(sysUserEntity, userForm));
        return Result.ok().putResult("修改成功");
    }

}
