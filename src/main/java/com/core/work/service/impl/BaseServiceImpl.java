package com.core.work.service.impl;

import com.core.work.entity.AbstractEntity;
import com.core.work.repository.BaseRepository;
import com.core.work.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


/**
 * @Description: 抽象Service实现层
 * @Author: 吴鹏
 * @Email: 694798354@qq.com
 * @Param:
 * @return
 * @date 2019/3/12 0012 上午 10:30
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<T extends AbstractEntity, R extends BaseRepository<T, String>> implements BaseService<T> {
    @Autowired
    protected R repository;

    @Override
    public T findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<T> findByIds(List<String> ids) {
        return repository.findByIdIn(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<String> ids) {
        List<T> entities = findByIds(ids);
        for (T entity : entities) {
            entity.setIsDelete(1);
        }
        repository.saveAll(entities);
    }

    @Override
    public List<T> findAll(){
        return repository.findAll();
    }

    @Override
    public boolean exist(String id) {
        return this.findById(id) != null;
    }

    @Override
    public List<String> getIdList(List<T> entities) {
        List<String> idList = new ArrayList<>();
        if (entities != null && entities.size() > 0) {
            for (T entity : entities) {
                idList.add(entity.getId());
            }
        }
        return idList;
    }

    @Override
    public T save(T entity) {
        return repository.save(entity);
    }
}
