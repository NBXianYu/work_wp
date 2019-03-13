package com.core.work.controller;

import com.core.work.utils.SheduleUtils;
import com.core.work.entity.SysUserEntity;
import com.core.work.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
public class HelloController {
    @Autowired
    SysUserService sysUserService;

    @ApiOperation(value = "测试接口", notes = "测试接口")
    @RequestMapping("/hello")
    public String helloBoot() throws ParseException {
        List<SysUserEntity> userList = sysUserService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date taskDate = dateFormat.parse("2019-03-13 09:33:00");
        for (SysUserEntity sysUserEntity : userList) {
            SheduleUtils.createUserTaskJob(sysUserEntity.getId(), taskDate);
        }
        return "Hello Boot-JPA";
    }

}
