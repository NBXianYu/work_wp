package com.core.modules.web.service.impl;

import com.core.common.utils.Result;
import com.core.modules.sys.service.impl.BaseServiceImpl;
import com.core.modules.web.repository.ComicRepository;
import com.core.modules.web.service.spec.ComicSpec;
import com.core.modules.web.entity.ComicEntity;
import com.core.modules.sys.entity.vo.ComicVo;
import com.core.modules.web.service.ComicService;
import com.core.common.utils.JpaPageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author 吴鹏
 * @Description:
 */
@Service
public class ComicServiceImpl extends BaseServiceImpl<ComicEntity, ComicRepository> implements ComicService{

    @Autowired
    ComicRepository comicRepository;


    @Override
    public Result getHome(Map<String, Object> params){
        Page<ComicEntity> entityPage = comicRepository.findAll(ComicSpec.where(params), JpaPageUtils.getPageRequest(params));
        // 存入结果的视图
        List<ComicVo> comicVoList = new ArrayList<>();
        // 遍历结果，将实体转换成视图
        entityPage.stream().map(value->ComicVo.getHomeVoByEntity(value)).forEach(value->comicVoList.add(value));
        return Result.ok().putResult(comicVoList).putTotal(entityPage.getTotalElements());
    }


}
