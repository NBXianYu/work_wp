package com.core.modules.web.controller;

import com.core.common.annotation.SysLog;
import com.core.common.validator.CheckDataUtils;
import com.core.common.validator.ValidatorUtils;
import com.core.common.validator.group.AddGroup;
import com.core.modules.sys.controller.AbstractController;
import com.core.modules.web.entity.ComicEntity;
import com.core.modules.sys.entity.SysUserEntity;
import com.core.modules.web.entity.form.ComicForm;
import com.core.modules.web.entity.vo.ComicVo;
import com.core.common.exception.BaseException;
import com.core.modules.web.service.ComicService;
import com.core.modules.sys.service.SysUserService;
import com.core.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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
    @Autowired
    private SysUserService sysUserService;

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

        return comicService.getHome(params);
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

    @GetMapping(value = "/detail")
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
        return Result.ok().putResult(ComicVo.getDetailVoByEntity(comicEntity));
    }

    @SysLog("添加漫画")
    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "添加漫画", notes = "添加漫画")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    public Result addOrUpdate (@RequestBody ComicForm comicForm) {
        ValidatorUtils.validateEntity(comicForm, AddGroup.class);
        if (comicForm == null ) {
            throw new BaseException("数据错误，请联系管理员");
        }
        // id不存在就是添加
        if (StringUtils.isBlank(comicForm.getId())) {
            comicService.save(ComicForm.getComicEntityByForm(comicForm, null));
        } else {
            ComicEntity comicEntity = comicService.findById(comicForm.getId());
            if (comicEntity == null) {
                throw new BaseException("数据未找到，请确认后重试");
            }
            comicService.save(ComicForm.getComicEntityByForm(comicForm, comicEntity));
        }
        return Result.ok();
    }

    @GetMapping(value = "/collect")
    @ApiOperation(value = "收藏漫画", notes = "收藏漫画")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    public Result collect () {
        List<ComicEntity> comicEntityList = comicService.findAll();

        SysUserEntity sysUserEntity = getUser();

        sysUserEntity.setComicEntitySet(new HashSet<>(comicEntityList));

        sysUserService.save(sysUserEntity);

        return Result.ok();
    }


}
