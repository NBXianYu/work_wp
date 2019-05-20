package com.core.modules.web.controller;

import com.core.common.utils.RedisUtils;
import com.core.common.utils.Result;
import com.core.modules.sys.controller.AbstractController;
import com.core.modules.sys.entity.SysUserEntity;
import com.core.modules.sys.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.builder.ToStringBuilder;
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
public class HelloController extends AbstractController {
    @Autowired
    SysUserService sysUserService;
    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "测试接口", notes = "测试接口")
    @GetMapping("/hello")
    public String helloBoot() throws ParseException {
        System.out.println(getUser());
        return "Hello Boot-JPA";
    }

    @ApiOperation(value = "测试redis接口", notes = "测试redis接口")
    @GetMapping("/redis")
    public Result test() {
        SysUserEntity sysUserEntity = redisUtils.get("user", SysUserEntity.class);
        if (sysUserEntity != null) {
            logger.error(sysUserEntity.toString());
        } else {
            SysUserEntity user = new SysUserEntity();
            user.setUserName("wupeng");
            user.setPassWord("pass1234");
            user.setPhone("13666666666");
            user.setStatus(0);
            redisUtils.set("user", user);
        }
        return Result.ok().put("I say : ", "Hello peng");
    }
}
