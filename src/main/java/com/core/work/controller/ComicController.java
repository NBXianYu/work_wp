package com.core.work.controller;

import com.core.work.entity.ComicEntity;
import com.core.work.exception.BaseException;
import com.core.work.service.ComicService;
import com.core.work.utils.Result;
import com.core.work.validation.CheckDataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author 吴鹏
 * @Description: 漫画相关接口
 */
@Api(value = "漫画接口")
@RestController
@RequestMapping("/comic")
public class ComicController extends AbstractController {

    @Autowired
    private ComicService comicService;

    @GetMapping(value = "/home")
    @ApiOperation(value = "漫画首页", notes = "漫画首页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(name = "currPage", value = "当前页数", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "sort", value = "升序：ASC；降序 DESC", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "orderField", value = "排序字段，多个字段排序以下划线分开，例如：name_description_uesrName", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "keyword", value = "漫画名字：模糊查询", paramType = "query", dataType = "String", required = false),
            @ApiImplicitParam(name = "time", value = "时间戳：传入时间戳时间当天创建的漫画", paramType = "query", dataType = "String", required = false)
    })
    public Result getHome(@RequestParam Map<String, Object> params) {

        return Result.ok().putResult(comicService.getHome(params));
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除漫画", notes = "删除漫画")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(name = "ids", value = "要删除的ids", paramType = "header", dataType = "List<String>", required = true)
    })
    public Result deleteComic(@RequestParam List<String> ids) {

        comicService.deleteByIds(ids);

        return Result.ok();
    }

    @GetMapping(value = "/info")
    @ApiOperation(value = "漫画详情", notes = "漫画详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true),
            @ApiImplicitParam(name = "id", value = "需要获取的漫画详情的id", paramType = "query", dataType = "String", required = true)
    })
    public Result getInfo(@RequestParam("id") String id) {
        CheckDataUtils.isBlank(id, "ID不能为空");

        ComicEntity comicEntity = comicService.findById(id);
        if (comicEntity == null) {
            throw new BaseException("未找到漫画详情，请刷新后重试");
        }
        return Result.ok().putResult(ComicEntity.getMiniComicVoByEntity(comicEntity));
    }

}
