package com.core.modules.sys.controller;

import com.core.common.utils.Result;
import com.core.modules.sys.service.SysLogService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author 吴鹏
 * @Description:
 */
@RestController
@RequestMapping("/sys/log")
public class SysLogController extends AbstractController {
    @Autowired
    private SysLogService sysLogService;

    @GetMapping(value = "/home")
    @ApiOperation(value = "日志列表", notes = "日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(name = "currPage", value = "当前页数", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "sort", value = "升序：ASC；降序 DESC", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "orderField", value = "排序字段，多个字段排序以下划线分开，例如：name_description_uesrName", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "keyword", value = "操作的模糊查询", paramType = "query", dataType = "String", required = false)
    })
    public Result list(@RequestParam Map<String, Object> params){
        return sysLogService.getHome(params);
    }

}
