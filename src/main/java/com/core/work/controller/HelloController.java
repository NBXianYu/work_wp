package com.core.work.controller;

import com.core.work.service.SysUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @Description:
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/1/28 0028 下午 15:21
 */
@RestController
@RequestMapping("/test")
public class HelloController extends AbstractController{
    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "测试接口", notes = "测试接口")
    @GetMapping("/hello")
    public String helloBoot() throws ParseException {
        System.out.println(getUser());
        return "Hello Boot-JPA";
    }
}
