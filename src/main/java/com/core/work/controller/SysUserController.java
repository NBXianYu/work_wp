package com.core.work.controller;

import com.core.work.entity.SysUserEntity;
import com.core.work.entity.vo.SysUserVo;
import com.core.work.exception.BaseException;
import com.core.work.form.UserForm;
import com.core.work.utils.Result;
import com.core.work.service.SysUserService;
import com.core.work.validation.CheckDataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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


    @GetMapping(value = "/home")
    @ApiOperation(value = "用户首页", notes = "用户首页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(name = "currPage", value = "当前页数", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "sort", value = "升序：ASC；降序 DESC", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "orderField", value = "排序字段，多个字段排序以下划线分开，例如：name_description_uesrName", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "keyword", value = "漫画名字：模糊查询", paramType = "query", dataType = "String", required = false)
    })
    public Result getHome(@RequestParam Map<String, Object> params) {

        return Result.ok().putResult(sysUserService.getHome(params));
    }

    @GetMapping(value = "/detail")
    @ApiOperation(value = "用户详情", notes = "用户详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "query", dataType = "String", required = true)
    })
    public Result getUserDetail(@RequestParam String userId) {
        CheckDataUtils.isBlank(userId, "用户ID不能为空");
        SysUserEntity sysUserEntity = sysUserService.findById(userId);
        if (sysUserEntity == null) {
            throw new BaseException("不存在当前用户");
        }
        return Result.ok().putResult(SysUserVo.getDetailVoByEntity(sysUserEntity));
    }

    /**
     * @Description: 修改用户信息
     * @Author: 吴鹏
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
