package com.core.work.controller;

import com.core.work.utils.Result;
import com.core.work.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/28 0028 下午 15:21
 */
@Api(description = "用户登录接口")
@RestController
@RequestMapping("/login")
public class SysUserController extends AbstractController{
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户名", value = "name", dataType = "String", paramType = "query",required = false)
    })
    @GetMapping("/findAll")
    public Result addLogin() {
        return Result.ok().putResult(sysUserService.findAll());
    }

}
