package com.core.work.controller;

import com.alibaba.fastjson.JSONObject;
import com.core.work.utils.SpringContextUtils;
import com.core.work.service.SysUserService;
import io.swagger.annotations.Api;
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
public class SysUserController extends BaseController{
    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/findAll")
    public String addLogin() {
        SysUserService sysUserService = (SysUserService) SpringContextUtils.getBean("sysUserService");
//        SysUserEntity sysUserEntity = new SysUserEntity();
//        sysUserEntity.setUsername("吴鹏");
//        sysUserEntity.setPassWord("pass");
//        sysUserService.addSysUser(sysUserEntity);

        return JSONObject.toJSON(sysUserService.findAll()).toString();
    }

}
