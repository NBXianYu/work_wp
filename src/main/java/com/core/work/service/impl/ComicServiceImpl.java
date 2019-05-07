package com.core.work.service.impl;

import com.core.work.entity.ComicEntity;
import com.core.work.entity.vo.MiniComicVo;
import com.core.work.repository.ComicRepository;
import com.core.work.service.ComicService;
import com.core.work.service.spec.ComicSpec;
import com.core.work.utils.JpaPageUtils;
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
    public Map<String, Object> getHome(Map<String, Object> params){
        Page<ComicEntity> entityPage = comicRepository.findAll(ComicSpec.where(params), JpaPageUtils.getPageRequest(params));
        // 结果map
        Map<String, Object> resultMap = new HashMap<>(5);
        // 存入结果的视图
        List<MiniComicVo> comicVoList = new ArrayList<>();
        // 遍历结果，将实体转换成视图
        entityPage.stream().map(value->ComicEntity.getMiniComicVoByEntity(value)).forEach(value->comicVoList.add(value));
        resultMap.put("entityList",comicVoList);
        resultMap.put("totalElements",entityPage.getTotalElements());
        resultMap.put("totalPages",entityPage.getTotalPages());
        resultMap.put("size",entityPage.getSize());

        return resultMap;
    }


}
